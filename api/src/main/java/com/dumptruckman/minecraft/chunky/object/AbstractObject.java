package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.json.JSONException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author dumptruckman, SwearWord
 */
public abstract class AbstractObject implements ChunkyObject {

    /**
     * Returns the child <code>TreeNode</code> at index
     * <code>childIndex</code>.
     */
    protected AbstractObject owner;
    private HashMap<String, HashSet<ChunkyObject>> ownables = new HashMap<String, HashSet<ChunkyObject>>();
    private HashMap<String, HashSet<ChunkyGroup>> groups = new HashMap<String, HashSet<ChunkyGroup>>();

    private String id;
    private final String className;

    public AbstractObject() {
        super();
        try {
            getData().put("name", "");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        className = this.getClass().getName();
    }

    public final boolean save() throws ChunkyObjectNotInitializedException {
        if (id == null) throw new ChunkyObjectNotInitializedException("Object cannot be saved without ID!");
        DatabaseManager.getDatabase().updateObject(this);
        return true;
    }

    public final void delete() {
        DatabaseManager.getDatabase().deleteObject(this);
        ChunkyManager.unregisterObject(this);
    }

    public final String getName() {
        try {
            return getData().getString("name");
        } catch (JSONException e) {
            Logging.severe(e.getMessage());
            return null;
        }
    }

    public final String getId() {
        return id;
    }

    public final AbstractObject setId(String id) {
        this.id = id;
        ChunkyManager.registerObject(this);
        return this;
    }

    public final String getType() {
        return className;
    }

    public final String getFullId() {
        return className + ":" + id;
    }

    public final AbstractObject setName(String name) {
        ChunkyObjectNameEvent event = new ChunkyObjectNameEvent(this, name);
        Chunky.getModuleManager().callEvent(event);
        if (event.isCancelled()) return this;
        try {
            getData().put("name", event.getNewName());
        } catch (JSONException e) {
            //Logging.warning(e.getMessage());
        }
        save();
        return this;
    }

    public final int hashCode() {
        return getFullId().hashCode();
    }

    public final boolean equals(Object obj) {
        return obj instanceof AbstractObject && ((AbstractObject) obj).getFullId().equals(this.getFullId());
    }

    public final boolean isOwned() {
        return !(owner == null);
    }

    /**
     * Causes o to be owned by this object.
     *
     * @param o object to become owned.
     * @return true if this object did not already own o.
     */
    private boolean addOwnable(AbstractObject o) {
        if (o == null)
            throw new IllegalArgumentException();

        // If owner is a owned by the child, remove owner from tree first.
        // ex. town.setOwner(Peasant) Peasant is removed from parent (Town).

        if (this.isOwnedBy(o) && this.owner != null) {
            this.getOwner().removeOwnableAndTakeChildren(this);
        }
        if (ownables.containsKey(o.getType())) {
            Boolean exists = ownables.get(o.getType()).add(o);
            if (exists) DatabaseManager.getDatabase().addOwnership(this, o);
            return exists;
        } else {
            HashSet<AbstractObject> ownables = new HashSet<AbstractObject>();
            ownables.add(o);
            this.ownables.put(o.getType(), ownables);
            
            DatabaseManager.getDatabase().addOwnership(this, o);
            return true;
        }
    }

    /**
     * Removes an ownable o from this object.
     *
     * @param o the ownable to remove
     * @return true if the set contained the specified element
     */
    private boolean removeOwnable(AbstractObject o) {
        Boolean removed = ownables.containsKey(o.getType()) && ownables.get(o.getType()).remove(o);
        if (removed) {
            o.owner = null;
            DatabaseManager.getDatabase().removeOwnership(this, o);
        }
        return removed;
    }

    private void removeOwnableAndTakeChildren(AbstractObject o) {
        if (removeOwnable(o)) takeChildren(o);
    }

    public final void takeChildren(AbstractObject o) {
        // If a child is removed, parent reposesses all their objects.
        // ex. If a child of a town is removed then their plots are owned by town.

        HashMap<String, HashSet<AbstractObject>> reposess = o.getOwnables();
        o.ownables = new HashMap<String, HashSet<AbstractObject>>();
        for (String key : reposess.keySet()) {
            for (AbstractObject co : reposess.get(key)) {
                this.addOwnable(co);
            }
        }
    }

    public final void removeChildren() {
        //Removes all children.

        HashMap<String, HashSet<AbstractObject>> reposess = this.getOwnables();
        this.ownables = new HashMap<String, HashSet<AbstractObject>>();
        for (String key : reposess.keySet()) {
            for (AbstractObject co : reposess.get(key)) {
                co.setOwner(null, true, true);}}
    }

    /**
     * Checks if o is owned by this object.
     *
     * @param o object to hasPerm ownership for
     * @return true if this object owns o
     */
    public final boolean isOwnerOf(AbstractObject o) {
        return o.isOwnedBy(this);
    }

    /**
     * @param owner Check if this object is an ancestor.
     * @return
     */
    public final boolean isOwnedBy(AbstractObject owner) {
        if (owner == null)
            return false;
        AbstractObject current = this;
        while (current != null && current != owner)
            current = current.getOwner();
        return current == owner;
    }

    public final boolean isDirectlyOwnedBy(AbstractObject owner) {
        return this.owner == owner;
    }

    /**
     * Returns the owner <code>TreeNode</code> of the receiver.
     */
    public final AbstractObject getOwner() {
        return owner;
    }

    /**
     * @param object       the object that will become the owner.
     * @param keepChildren false transfers the object's children to current owner.
     */
    public final void setOwner(AbstractObject object, Boolean keepChildren, boolean clearPermissions) {
        if (owner != null) {
            if (keepChildren)
                owner.removeOwnable(this);
            else
                owner.removeOwnableAndTakeChildren(this);
        }
        else if(!keepChildren) {
            this.removeChildren();
        }
        if (object != null) {
            if (object.addOwnable(this))
                owner = object;
        } else {
            owner = null;
        }
        if (clearPermissions) {
            ChunkyManager.getAllPermissions(this).clear();
            DatabaseManager.getDatabase().removeAllPermissions(this);
        }
    }

    public final Boolean hasDefaultPerm(PermissionFlag type) {
        PermissionRelationship perms = ChunkyManager.getPermissions(this, this);
        Logging.debug("default perms: " + perms + " contains " + type + "?");
        return perms.hasFlag(type);
    }

    public final PermissionRelationship getDefaultPerms() {
        return ChunkyManager.getPermissions(this, this);
    }

    public final void setDefaultPerms(HashMap<PermissionFlag, Boolean> flags) {
        if (flags == null) {
            ChunkyManager.getPermissions(this, this).clearFlags();
            DatabaseManager.getDatabase().removePermissions(this, this);
            return;
        }

        for (Map.Entry<PermissionFlag, Boolean> flag : flags.entrySet()) {
            setDefaultPerm(flag.getKey(), flag.getValue());
        }
    }

    public final void setDefaultPerm(PermissionFlag type, boolean status) {
        setDefaultPerm(type, status, true);
    }

    public final void setDefaultPerm(PermissionFlag type, boolean status, boolean persist) {
        PermissionRelationship perms = ChunkyManager.getPermissions(this, this);
        // Set flag
        perms.setFlag(type, status);

        // Persist if requested
        if (persist) {
            DatabaseManager.getDatabase().updateDefaultPermissions(this, perms);
        }
    }

    /**
     * Returns true if the receiver is a leaf.
     */
    public final boolean isLeaf() {
        return ownables.size() == 0;
    }


    /**
     * Returns all ownables of this object.  You may not change the structure/values of this HashMap.
     *
     * @return
     */
    public final HashMap<String, HashSet<AbstractObject>> getOwnables() {
        @SuppressWarnings("unchecked")
        HashMap<String, HashSet<AbstractObject>> ownables = (HashMap<String, HashSet<AbstractObject>>) this.ownables.clone();
        return ownables;
    }

    public void addToGroup(ChunkyGroup group) {
        group._addMember(this);
        HashSet<ChunkyGroup> groupsOfType = groups.get(group.getType());
        if (groupsOfType == null) {
            groupsOfType = new HashSet<ChunkyGroup>();
            groups.put(group.getType(), groupsOfType);
        }
        groupsOfType.add(group);
        DatabaseManager.getDatabase().addGroupMember(group, this);
    }

    protected void _addGroup(ChunkyGroup group) {
        HashSet<ChunkyGroup> groupsOfType = groups.get(group.getType());
        if (groupsOfType == null) {
            groupsOfType = new HashSet<ChunkyGroup>();
            groups.put(group.getType(), groupsOfType);
        }
        groupsOfType.add(group);
    }

    public void removeFromGroup(ChunkyGroup group) {
        group._removeMember(this);
        HashSet<ChunkyGroup> groupsOfType = groups.get(group.getType());
        if (groupsOfType != null) {
            groupsOfType.remove(group);
        }
        DatabaseManager.getDatabase().removeGroupMember(group, this);
    }

    protected void _removeGroup(ChunkyGroup group) {
        HashSet<ChunkyGroup> groupsOfType = groups.get(group.getType());
        if (groupsOfType != null) {
            groupsOfType.remove(group);
        }
    }

    public HashMap<String, HashSet<ChunkyGroup>> getGroups() {
        return groups;
    }
}
