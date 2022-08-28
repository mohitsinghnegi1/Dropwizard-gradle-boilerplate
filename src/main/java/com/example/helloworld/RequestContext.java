package com.example.helloworld;

import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;

public class RequestContext {

    private static final String REQUEST_ID = "reqId";
    private static final String TRANSACTION_ID = "txnId";
    private static final String SELLER_ID = "sellerId";
    private static final String CLIENT_ID = "clientId";
    private static final String CURRENT_USER = "currentUser";
    private static final String TRANSACTION_MANAGER = "transactionManager";

    private static ThreadLocal<Map<String, Object>>
            requestAttributes =
            new ThreadLocal<Map<String, Object>>() {
                @Override
                protected Map<String, Object> initialValue() {
                    return newHashMap();
                }
            };

    public static String getRequestId() {
        return (String) requestAttributes.get().get(REQUEST_ID);
    }

    public static void setRequestId(String requestId) {
        requestAttributes.get().put(REQUEST_ID, requestId);
    }

    public static String getTransactionManager() {
        if(requestAttributes.get().get(TRANSACTION_MANAGER) == null) {
            return "default";
        } else {
            return (String) requestAttributes.get().get(TRANSACTION_MANAGER);
        }
    }

    public static void setTransactionManager(String transactionManagerName) {
        requestAttributes.get().put(TRANSACTION_MANAGER, transactionManagerName);
    }

    public static String getTransactionId() {
        return (String) requestAttributes.get().get(TRANSACTION_ID);
    }

    public static void setTransactionId(String transactionId) {
        requestAttributes.get().put(TRANSACTION_ID, transactionId);
    }

    /**
     *
     * @Warning this method will start throwing an exception when the header is missing
     * @return seller id
     */
    public static String getSellerId() {
        return (String) requestAttributes.get().get(SELLER_ID);
    }

    public static void setSellerId(String sellerId) {
        requestAttributes.get().put(SELLER_ID, sellerId);
    }

    public static String getClientId() throws Exception {
        return Optional.ofNullable((String) requestAttributes.get().get(CLIENT_ID))
                .orElseThrow(() -> {
                    Exception exception = new Exception("MISSING_CLIENT_ID");

                    return exception;
                });
    }

    public static void setClientId(String clientId) {
        requestAttributes.get().put(CLIENT_ID, clientId);
    }

    public static String getCurrentUser() {
        return (String) requestAttributes.get().get(CURRENT_USER);
    }

    public static void setCurrentUser(String currentUser) {
        requestAttributes.get().put(CURRENT_USER, currentUser);
    }

    public static <T> T getAttribute(String name) {
        return (T) requestAttributes.get().get(name);
    }

    public static <T> void setAttribute(String name, T object) {
        requestAttributes.get().put(name, object);
    }

    public static Map getCopyofContextMap() {
        return requestAttributes.get();
    }

    public static void setContextMap(Map contextMap) {
        requestAttributes.set(contextMap);
    }

    public static void clear() {
        requestAttributes.get().clear();
        requestAttributes.remove();
    }

    public static void clear(String key) {
        requestAttributes.get().remove(key);
        if(requestAttributes.get().isEmpty()) {
            requestAttributes.remove();
        }
    }
}

