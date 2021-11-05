# What is RudderStack?

[**RudderStack**](https://rudderstack.com/) is a **customer data platform for developers**. Our tooling makes it easy to deploy pipelines that collect customer data from every app, website and SaaS platform, then activate it in your warehouse and business tools.

More information on RudderStack can be found [**here**](https://github.com/rudderlabs/rudder-server).

## What is the RudderStack Cordova SDK?

[**Apache Cordova**](https://cordova.apache.org/) is an open-source, cross-platform application development framework. The RudderStack Cordova SDK lets you track event data from your Cordova app and send it to your preferred destination platforms via RudderStack.

> For detailed documentation on the Cordova SDK, click [**here**](https://docs.rudderstack.com/stream-sources/rudderstack-sdk-integration-guides/rudderstack-cordova-sdk). 

## Getting started

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

## Send events
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

## Contact us
If you come across any issues while configuring or using RudderStack, please feel free to [**contact us**](https://rudderstack.com/contact/) or start a conversation on our [**Slack**](https://resources.rudderstack.com/join-rudderstack-slack) channel. We will be happy to help you.
