# What is Rudder?

**Short answer:**
Rudder is an open-source Segment alternative written in Go, built for the enterprise.

**Long answer:**
Rudder is a platform for collecting, storing and routing customer event data to dozens of tools. Rudder is open-source, can run in your cloud environment (AWS, GCP, Azure or even your data-centre) and provides a powerful transformation framework to process your event data on the fly.

Released under [MIT License](https://opensource.org/licenses/MIT)

## Getting Started with Cordova SDK

To add the SDK as a dependency navigate to the root folder of your application and run the following command:

```bash
cordova plugin add rudder-sdk-cordova
```

## Initialize `RudderClient`
Add the following code in the `onDeviceReady()` function of your home page to initialize the SDK.

```javascript
RudderClient.initialize( <WRITE_KEY> , {
  "dataPlaneUrl": <DATA_PLANE_URL> ,
  "trackLifecycleEvents": true,
  "controlPlaneUrl": "https://api.rudderstack.com"
})
 ```

## Send Events
An example `track` call is as below
```javascript
    RudderClient.track("test_track_event", {
        "test_property_1" : "test_value_1"
    });
```

## Anonymous ID

You can use the `setAnonymousId` method to override the default `anonymousId`, as shown:

```javascript
RudderClient.setAnonymousId("sampleAnonymousId");
```
* You need to call `setAnonymousId` method before calling `initialize`


## Advertisement ID

RudderStack collects the advertisement ID if it is enabled by the user. To set the advertising ID yourself, you can use the `setAdvertisingId` method as shown:

```javascript
RudderClient.setAdvertisingId("SampleAdvertisingId")
```

## Setting the device token

You can pass your `device-token` for push notifications to be passed to the destinations which support the **Push Notifications** feature. RudderStack sets the `token` under `context.device.token`.

An example of setting the `device-token` is as shown:

```javascript
RudderClient.putDeviceToken("sampleDeviceToken");
```

## Contact Us
If you come across any issues while configuring or using RudderStack, please feel free to [contact us](https://rudderstack.com/contact/) or start a conversation on our [Slack](https://resources.rudderstack.com/join-rudderstack-slack) channel. We will be happy to help you.
