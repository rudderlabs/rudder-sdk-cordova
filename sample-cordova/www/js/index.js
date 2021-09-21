/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);

document.getElementById("track").onclick = function () {
    RudderClient.track("Testing Event", { "test_type": "initial" }, { "integrations": { "All": false, "Amplitude": true } })

}

async function onDeviceReady() {
    // Cordova is now initialized. Have fun!
    RudderClient.setAnonymousId("SampleAnonId");
    await RudderClient.initialize("1n0JdVPZTRUIkLXYccrWzZwdGSx", {
        "dataPlaneUrl": "https://22d3-175-101-36-4.ngrok.io",
        "flushQueueSize": 30,
        "dbCountThreshold": 10000,
        "configRefreshInterval": 2,
        "logLevel": RudderClient.LogLevel.VERBOSE,
        "sleepTimeOut": 10,
        "trackLifecycleEvents": true,
        "controlPlaneUrl": "https://api.rudderstack.com",
        "factories": [RudderAppCenterFactory]
    }, {
        "integrations": {
            "MixPanel": true,
            "Amplitude": true
        }
    })
    RudderClient.identify("userId", {
        "address": {
            "city": "Hyderabad",
            "country": "India",
            "state": "Telangana",
        },
        "birthday": "17/07/1984",
        "company": {
            "name": "RudderStack",
            "id": "RS",
            "industry": "IT"
        },
        "email": "john@rudderstack.com",
        "firstName": "john",
    }, {
        "externalIds": {
            "brazeExternalId": "externalId1"
        },
        "integrations": {
            "MixPanel": false,
            "Amplitude": true
        }
    })
    RudderClient.putDeviceToken("SampleDeviceToken")
    RudderClient.setAdvertisingId("SampleAdvertisingId")
    RudderClient.group("group1", { "groupname": "RS", "groupwork": "Mobile dev" }, { "integrations": { "All": false, "Amplitude": true } })
    RudderClient.track('Order Completed', {
        checkout_id: '12345',
        order_id: '1234',
        affiliation: 'Apple Store',
        total: 20,
        revenue: 15.00,
        shipping: 22,
        tax: 1,
        discount: 1.5,
        coupon: 'ImagePro',
        currency: 'USD',
        products: [
            {
                product_id: '123',
                sku: 'G-32',
                name: 'Monopoly',
                price: 14,
                quantity: 1,
                category: 'Games',
                url: 'https://www.website.com/product/path',
                image_url: 'https://www.website.com/product/path.jpg'
            },
            {
                product_id: '345',
                sku: 'F-32',
                name: 'UNO',
                price: 3.45,
                quantity: 2,
                category: 'Games'
            }
        ]
    }, { "integrations": { "All": false, "Amplitude": true } })
    RudderClient.screen("Home Screen", { "mobile": "pixel" }, { "integrations": { "All": false, "Amplitude": true } })
    RudderClient.alias("tempUserId", { "integrations": { "All": false, "Amplitude": true } })
    RudderClient.reset()
    RudderClient.flush()
    console.log('Running cordova-' + cordova.platformId + '@' + cordova.version);
    // RudderClient.identify();
    document.getElementById('deviceready').classList.add('ready');
}
