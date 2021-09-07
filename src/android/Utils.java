package com.rudderstack.analytics.cordova;

import com.google.gson.Gson;
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

    static String optArgString(JSONArray args, int index) {
        return args.isNull(index) ? null : args.optString(index);
    }

    static RudderConfig getRudderConfig(JSONObject configJson) {
        if (configJson == null) {
            return new RudderConfig.Builder().build();
        }
        Map<String, Object> configMap = getMapFromJSON(configJson);
        RudderConfig.Builder configBuilder = new RudderConfig.Builder();
        if (configMap.containsKey("dataPlaneUrl") && getString(configMap.get("dataPlaneUrl")) != null) {
            configBuilder = configBuilder.withDataPlaneUrl(getString(configMap.get("dataPlaneUrl")));
        }
        if (configMap.containsKey("flushQueueSize") && getInteger(configMap.get("flushQueueSize")) != null) {
            configBuilder = configBuilder.withFlushQueueSize(getInteger(configMap.get("flushQueueSize")));
        }
        if (configMap.containsKey("dbCountThreshold") && getInteger(configMap.get("dbCountThreshold")) != null) {
            configBuilder = configBuilder.withDbThresholdCount(getInteger(configMap.get("dbCountThreshold")));
        }
        if (configMap.containsKey("configRefreshInterval") && getInteger(configMap.get("configRefreshInterval")) != null) {
            configBuilder = configBuilder.withConfigRefreshInterval(getInteger(configMap.get("configRefreshInterval")));
        }
        if (configMap.containsKey("logLevel") && getInteger(configMap.get("logLevel")) != null) {
            configBuilder = configBuilder.withLogLevel(getInteger(configMap.get("logLevel")));
        }
        if (configMap.containsKey("sleepTimeOut") && getInteger(configMap.get("sleepTimeOut")) != null) {
            configBuilder = configBuilder.withSleepCount(getInteger(configMap.get("sleepTimeOut")));
        }
        if (configMap.containsKey("trackLifecycleEvents")) {
            configBuilder = configBuilder.withTrackLifecycleEvents(getBoolean(configMap.get("trackLifecycleEvents")));
        }
        if (configMap.containsKey("recordScreenViews")) {
            configBuilder = configBuilder.withRecordScreenViews(getBoolean(configMap.get("recordScreenViews")));
        }
        if (configMap.containsKey("controlPlaneUrl") && getString(configMap.get("controlPlaneUrl")) != null) {
            configBuilder = configBuilder.withControlPlaneUrl(getString(configMap.get("controlPlaneUrl")));
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
                option.putIntegration(entry.getKey(), (boolean) entry.getValue());
            }
        }
        return option;
    }

    static RudderTraits getRudderTraits(JSONObject traitsJson) {
        if (traitsJson == null) {
            return null;
        }

        Map<String, Object> traitsMap = getMapFromJSON(traitsJson);
        RudderTraitsBuilder builder = new RudderTraitsBuilder();
        if (traitsMap.containsKey("address")) {
            Map<String, Object> addressMap = (Map<String, Object>) traitsMap.get(
                    "address"
            );
            if (addressMap != null) {
                if (addressMap.containsKey("city")) {
                    builder.setCity((String) addressMap.get("city"));
                }
                if (addressMap.containsKey("country")) {
                    builder.setCountry((String) addressMap.get("country"));
                }
                if (addressMap.containsKey("postalCode")) {
                    builder.setPostalCode((String) addressMap.get("postalCode"));
                }
                if (addressMap.containsKey("state")) {
                    builder.setState((String) addressMap.get("state"));
                }
                if (addressMap.containsKey("street")) {
                    builder.setStreet((String) addressMap.get("street"));
                }
            }
            traitsMap.remove("address");
        }
        if (traitsMap.containsKey("age") && traitsMap.get("age") != null) {
            builder.setAge(Integer.parseInt((String) traitsMap.get("age")));
            traitsMap.remove("age");
        }
        if (traitsMap.containsKey("birthday")) {
            builder.setBirthDay((String) traitsMap.get("birthday"));
            traitsMap.remove("birthday");
        }
        if (traitsMap.containsKey("company")) {
            Map<String, Object> companyMap = (Map<String, Object>) traitsMap.get(
                    "company"
            );
            if (companyMap != null) {
                if (companyMap.containsKey("name")) {
                    builder.setCompanyName((String) companyMap.get("name"));
                }
                if (companyMap.containsKey("id")) {
                    builder.setCompanyId((String) companyMap.get("id"));
                }
                if (companyMap.containsKey("industry")) {
                    builder.setIndustry((String) companyMap.get("industry"));
                }
            }
            traitsMap.remove("company");
        }
        if (traitsMap.containsKey("createdAt")) {
            builder.setCreateAt((String) traitsMap.get("createdAt"));
            traitsMap.remove("createdAt");
        }
        if (traitsMap.containsKey("description")) {
            builder.setDescription((String) traitsMap.get("description"));
            traitsMap.remove("description");
        }
        if (traitsMap.containsKey("email")) {
            builder.setEmail((String) traitsMap.get("email"));
            traitsMap.remove("email");
        }
        if (traitsMap.containsKey("firstName")) {
            builder.setFirstName((String) traitsMap.get("firstName"));
            traitsMap.remove("firstName");
        }
        if (traitsMap.containsKey("gender")) {
            builder.setGender((String) traitsMap.get("gender"));
            traitsMap.remove("gender");
        }
        if (traitsMap.containsKey("id")) {
            builder.setId((String) traitsMap.get("id"));
            traitsMap.remove("id");
        }
        if (traitsMap.containsKey("lastName")) {
            builder.setLastName((String) traitsMap.get("lastName"));
            traitsMap.remove("lastName");
        }
        if (traitsMap.containsKey("name")) {
            builder.setName((String) traitsMap.get("name"));
            traitsMap.remove("name");
        }
        if (traitsMap.containsKey("phone")) {
            builder.setPhone((String) traitsMap.get("phone"));
            traitsMap.remove("phone");
        }
        if (traitsMap.containsKey("title")) {
            builder.setTitle((String) traitsMap.get("title"));
            traitsMap.remove("title");
        }
        if (traitsMap.containsKey("userName")) {
            builder.setUserName((String) traitsMap.get("userName"));
            traitsMap.remove("userName");
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
            if ((Integer) value > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    static List<Object> getListFromJSON(JSONArray jsonArray) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0, count = jsonArray.length(); i < count; i++) {
            Object value = getObject(jsonArray.opt(i));
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }


}
