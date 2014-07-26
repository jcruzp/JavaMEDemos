
package com.jcruz.demos.test;

import com.oracle.json.JsonException;
import com.oracle.json.JsonObject;

public final class TwitterJsonObjectType {
    public enum Type {
        SENDER,
        STATUS,
        DIRECT_MESSAGE,
        DELETE,
        LIMIT,
        STALL_WARNING,
        SCRUB_GEO,
        FRIENDS,
        FAVORITE,
        UNFAVORITE,
        FOLLOW,
        UNFOLLOW,
        USER_LIST_MEMBER_ADDED,
        USER_LIST_MEMBER_DELETED,
        USER_LIST_SUBSCRIBED,
        USER_LIST_UNSUBSCRIBED,
        USER_LIST_CREATED,
        USER_LIST_UPDATED,
        USER_LIST_DESTROYED,
        USER_UPDATE,
        BLOCK,
        UNBLOCK,
        DISCONNECTION,
        UNKNOWN
    }


      /**
     * Determine the respective object type for a given JSONObject.  This
     * method inspects the object to figure out what type of object it
     * represents.  This is useful when processing JSON events of mixed type
     * from a stream, in which case you may need to know what type of object
     * to construct, or how to handle the event properly.
     *
     * @param json the JSONObject whose type should be determined
     * @return the determined TwitterJsonObjectType, or null if not recognized
     */
    public static Type determine(JsonObject json) {
        // This code originally lived in AbstractStreamImplementation.
        // I've moved it in here to expose it as a public encapsulation of
        // the object type determination logic.
        if (!json.isNull("sender")) {
            return Type.SENDER;
        } else if (!json.isNull("text")) {
            return Type.STATUS;
        } else if (!json.isNull("direct_message")) {
            return Type.DIRECT_MESSAGE;
        } else if (!json.isNull("delete")) {
            return Type.DELETE;
        } else if (!json.isNull("limit")) {
            return Type.LIMIT;
        } else if (!json.isNull("warning")) {
            return Type.STALL_WARNING;
        } else if (!json.isNull("scrub_geo")) {
            return Type.SCRUB_GEO;
        } else if (!json.isNull("friends")) {
            return Type.FRIENDS;
        } else if (!json.isNull("event")) {
            String event;
            try {
                event = json.getString("event");
                if ("favorite".equals(event)) {
                    return Type.FAVORITE;
                } else if ("unfavorite".equals(event)) {
                    return Type.UNFAVORITE;
                } else if ("follow".equals(event)) {
                    return Type.FOLLOW;
                } else if ("unfollow".equals(event)) {
                    return Type.UNFOLLOW;
                } else if (event.startsWith("list")) {
                    if ("list_member_added".equals(event)) {
                        return Type.USER_LIST_MEMBER_ADDED;
                    } else if ("list_member_removed".equals(event)) {
                        return Type.USER_LIST_MEMBER_DELETED;
                    } else if ("list_user_subscribed".equals(event)) {
                        return Type.USER_LIST_SUBSCRIBED;
                    } else if ("list_user_unsubscribed".equals(event)) {
                        return Type.USER_LIST_UNSUBSCRIBED;
                    } else if ("list_created".equals(event)) {
                        return Type.USER_LIST_CREATED;
                    } else if ("list_updated".equals(event)) {
                        return Type.USER_LIST_UPDATED;
                    } else if ("list_destroyed".equals(event)) {
                        return Type.USER_LIST_DESTROYED;
                    }
                } else if ("user_update".equals(event)) {
                    return Type.USER_UPDATE;
                } else if ("block".equals(event)) {
                    return Type.BLOCK;
                } else if ("unblock".equals(event)) {
                    return Type.UNBLOCK;
                }
            } catch (JsonException jsone) {
                try {
                    System.out.println("Failed to get event element... ");
                } catch (JsonException ignore) {
                }
            }
        } else if (!json.isNull("disconnect")) {
            return Type.DISCONNECTION;
        }
        return Type.UNKNOWN;
    }
}