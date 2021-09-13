package com.rudderstack.analytics.cordova;

import com.rudderstack.android.sdk.core.RudderConfig;
import com.rudderstack.android.sdk.core.RudderOption;
import com.rudderstack.android.sdk.core.RudderProperty;
import com.rudderstack.android.sdk.core.RudderTraits;
import com.rudderstack.android.sdk.core.RudderTraitsBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {

    private Utils() {
        throw new IllegalArgumentException("Utility class cannot be instantiated");
    }

    static String optArgString(JSONArray args, int index) {
        return args.isNull(index) ? null : args.optString(index);
    }

    static RudderConfig getRudderConfig(JSONObject configJson) {
        if (configJson == null) {
            return new RudderConfig.Builder().build();
        }
        Map<String, Object> configMap = getMapFromJSON(configJson);
        RudderConfig.Builder configBuilder = new RudderConfig.Builder();
        if (configMap.containsKey(RudderConfigConstants.dataPlaneUrl) && getString(configMap.get(RudderConfigConstants.dataPlaneUrl)) != null) {
            configBuilder = configBuilder.withDataPlaneUrl(getString(configMap.get(RudderConfigConstants.dataPlaneUrl)));
        }
        if (configMap.containsKey(RudderConfigConstants.flushQueueSize) && getInteger(configMap.get(RudderConfigConstants.flushQueueSize)) != null) {
            configBuilder = configBuilder.withFlushQueueSize(getInteger(configMap.get(RudderConfigConstants.flushQueueSize)));
        }
        if (configMap.containsKey(RudderConfigConstants.dbCountThreshold) && getInteger(configMap.get(RudderConfigConstants.dbCountThreshold)) != null) {
            configBuilder = configBuilder.withDbThresholdCount(getInteger(configMap.get(RudderConfigConstants.dbCountThreshold)));
        }
        if (configMap.containsKey(RudderConfigConstants.configRefreshInterval) && getInteger(configMap.get(RudderConfigConstants.configRefreshInterval)) != null) {
            configBuilder = configBuilder.withConfigRefreshInterval(getInteger(configMap.get(RudderConfigConstants.configRefreshInterval)));
        }
        if (configMap.containsKey(RudderConfigConstants.logLevel) && getInteger(configMap.get(RudderConfigConstants.logLevel)) != null) {
            configBuilder = configBuilder.withLogLevel(getInteger(configMap.get(RudderConfigConstants.logLevel)));
        }
        if (configMap.containsKey(RudderConfigConstants.sleepTimeOut) && getInteger(configMap.get(RudderConfigConstants.sleepTimeOut)) != null) {
            configBuilder = configBuilder.withSleepCount(getInteger(configMap.get(RudderConfigConstants.sleepTimeOut)));
        }
        if (configMap.containsKey(RudderConfigConstants.trackLifecycleEvents)) {
            configBuilder = configBuilder.withTrackLifecycleEvents(getBoolean(configMap.get(RudderConfigConstants.trackLifecycleEvents)));
        }
        if (configMap.containsKey(RudderConfigConstants.recordScreenViews)) {
            configBuilder = configBuilder.withRecordScreenViews(getBoolean(configMap.get(RudderConfigConstants.recordScreenViews)));
        }
        if (configMap.containsKey(RudderConfigConstants.controlPlaneUrl) && getString(configMap.get(RudderConfigConstants.controlPlaneUrl)) != null) {
            configBuilder = configBuilder.withControlPlaneUrl(getString(configMap.get(RudderConfigConstants.controlPlaneUrl)));
        }

        return configBuilder.build();
    }

    static RudderOption getRudderOption(JSONObject optionsJson) {
        if (optionsJson == null) {
            return null;
        }
        Map<String, Object> optionsMap = getMapFromJSON(optionsJson);
        RudderOption option = new RudderOption();
        if (optionsMap.containsKey("externalIds")) {
            Map<String, String> externalIdsMap = (Map<String, String>) optionsMap.get("externalIds");
            for (Map.Entry<String, String> entry : externalIdsMap.entrySet()) {
                option.putExternalId(entry.getKey(), entry.getValue());
            }
        }
        if (optionsMap.containsKey("integrations")) {
            Map<String, Object> integrationsMap = (Map<String, Object>) optionsMap.get("integrations");
            for (Map.Entry<String, Object> entry : integrationsMap.entrySet()) {
                option.putIntegration(entry.getKey(), getBoolean(entry.getValue()));
            }
        }
        return option;
    }

    static RudderTraits getRudderTraits(JSONObject traitsJson) {
        if (traitsJson == null)
            return null;


        Map<String, Object> traitsMap = getMapFromJSON(traitsJson);
        RudderTraitsBuilder builder = new RudderTraitsBuilder();
        if (traitsMap.containsKey(RudderTraitsConstants.address)) {
            Map<String, Object> addressMap = (Map<String, Object>) traitsMap.get(
                    RudderTraitsConstants.address
            );
            if (addressMap != null) {
                if (addressMap.containsKey(RudderTraitsConstants.city)) {
                    builder.setCity((String) addressMap.get(RudderTraitsConstants.city));
                }
                if (addressMap.containsKey(RudderTraitsConstants.country)) {
                    builder.setCountry((String) addressMap.get(RudderTraitsConstants.country));
                }
                if (addressMap.containsKey(RudderTraitsConstants.postalCode)) {
                    builder.setPostalCode((String) addressMap.get(RudderTraitsConstants.postalCode));
                }
                if (addressMap.containsKey(RudderTraitsConstants.state)) {
                    builder.setState((String) addressMap.get(RudderTraitsConstants.state));
                }
                if (addressMap.containsKey(RudderTraitsConstants.street)) {
                    builder.setStreet((String) addressMap.get(RudderTraitsConstants.street));
                }
            }
            traitsMap.remove(RudderTraitsConstants.address);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.age) && traitsMap.get(RudderTraitsConstants.age) != null) {
            builder.setAge(Integer.parseInt((String) traitsMap.get(RudderTraitsConstants.age)));
            traitsMap.remove(RudderTraitsConstants.age);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.birthday)) {
            builder.setBirthDay((String) traitsMap.get(RudderTraitsConstants.birthday));
            traitsMap.remove(RudderTraitsConstants.birthday);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.company)) {
            Map<String, Object> companyMap = (Map<String, Object>) traitsMap.get(
                    RudderTraitsConstants.company
            );
            if (companyMap != null) {
                if (companyMap.containsKey(RudderTraitsConstants.name)) {
                    builder.setCompanyName((String) companyMap.get(RudderTraitsConstants.name));
                }
                if (companyMap.containsKey(RudderTraitsConstants.id)) {
                    builder.setCompanyId((String) companyMap.get(RudderTraitsConstants.id));
                }
                if (companyMap.containsKey(RudderTraitsConstants.industry)) {
                    builder.setIndustry((String) companyMap.get(RudderTraitsConstants.industry));
                }
            }
            traitsMap.remove(RudderTraitsConstants.company);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.createdAt)) {
            builder.setCreateAt((String) traitsMap.get(RudderTraitsConstants.createdAt));
            traitsMap.remove(RudderTraitsConstants.createdAt);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.description)) {
            builder.setDescription((String) traitsMap.get(RudderTraitsConstants.description));
            traitsMap.remove(RudderTraitsConstants.description);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.email)) {
            builder.setEmail((String) traitsMap.get(RudderTraitsConstants.email));
            traitsMap.remove(RudderTraitsConstants.email);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.firstName)) {
            builder.setFirstName((String) traitsMap.get(RudderTraitsConstants.firstName));
            traitsMap.remove(RudderTraitsConstants.firstName);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.gender)) {
            builder.setGender((String) traitsMap.get(RudderTraitsConstants.gender));
            traitsMap.remove(RudderTraitsConstants.gender);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.id)) {
            builder.setId((String) traitsMap.get(RudderTraitsConstants.id));
            traitsMap.remove(RudderTraitsConstants.id);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.lastName)) {
            builder.setLastName((String) traitsMap.get(RudderTraitsConstants.lastName));
            traitsMap.remove(RudderTraitsConstants.lastName);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.name)) {
            builder.setName((String) traitsMap.get(RudderTraitsConstants.name));
            traitsMap.remove(RudderTraitsConstants.name);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.phone)) {
            builder.setPhone((String) traitsMap.get(RudderTraitsConstants.phone));
            traitsMap.remove(RudderTraitsConstants.phone);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.title)) {
            builder.setTitle((String) traitsMap.get(RudderTraitsConstants.title));
            traitsMap.remove(RudderTraitsConstants.title);
        }
        if (traitsMap.containsKey(RudderTraitsConstants.userName)) {
            builder.setUserName((String) traitsMap.get(RudderTraitsConstants.userName));
            traitsMap.remove(RudderTraitsConstants.userName);
        }
        RudderTraits traits = builder.build();
        for (Map.Entry<String, Object> entry : traitsMap.entrySet()) {
            traits.put(entry.getKey(), entry.getValue());
        }
        return traits;
    }

    static RudderProperty getRudderProperty(JSONObject propertiesJson) {
        if (propertiesJson == null) {
            return null;
        }
        return new RudderProperty().putValue(getMapFromJSON(propertiesJson));
    }

    static Map<String, Object> getMapFromJSON(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keysIter = jsonObject.keys();
        while (keysIter.hasNext()) {
            String key = keysIter.next();
            Object value = jsonObject.isNull(key) ? null : getObject(jsonObject.opt(key));

            if (value != null) {
                map.put(key, value);
            }
        }
        return map;
    }

    static Object getObject(Object value) {
        if (value instanceof JSONObject) {
            value = getMapFromJSON((JSONObject) value);
        } else if (value instanceof JSONArray) {
            value = getListFromJSON((JSONArray) value);
        }
        return value;
    }

    static String getString(Object value) {
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof Integer) {
            return String.valueOf(value);
        }
        return null;
    }

    static Integer getInteger(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof String) {
            return Integer.valueOf((String) value);
        }
        return null;
    }

    static Boolean getBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Integer) {
            return (Integer) value > 0;
        }
        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }
        return false;
    }

    static List<Object> getListFromJSON(JSONArray jsonArray) {
        List<Object> list = new ArrayList<>();
        for (int i = 0, count = jsonArray.length(); i < count; i++) {
            Object value = getObject(jsonArray.opt(i));
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }


}

class RudderConfigConstants {

    private RudderConfigConstants() {
        throw new IllegalArgumentException("RudderConfigConstants class cannot be instantiated");
    }

    static String dataPlaneUrl = "dataPlaneUrl";
    static String flushQueueSize = "flushQueueSize";
    static String dbCountThreshold = "dbCountThreshold";
    static String configRefreshInterval = "configRefreshInterval";
    static String logLevel = "logLevel";
    static String sleepTimeOut = "sleepTimeOut";
    static String controlPlaneUrl = "controlPlaneUrl";
    static String trackLifecycleEvents = "trackLifecycleEvents";
    static String recordScreenViews = "recordScreenViews";

}

class RudderTraitsConstants {
    private RudderTraitsConstants() {
        throw new IllegalArgumentException("RudderTraitsConstants class cannot be instantiated");
    }

    static String address = "address";
    static String birthday = "birthday";
    static String company = "company";
    static String createdAt = "createdAt";
    static String description = "description";
    static String email = "email";
    static String firstName = "firstName";
    static String gender = "gender";
    static String lastName = "lastName";
    static String phone = "phone";
    static String title = "title";
    static String userName = "userName";
    static String city = "city";
    static String country = "country";
    static String postalCode = "postalCode";
    static String state = "state";
    static String street = "street";
    static String age = "age";
    static String name = "name";
    static String id = "id";
    static String industry = "industry";
}
