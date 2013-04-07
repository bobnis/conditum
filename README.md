# conditum [![Build Status](https://travis-ci.org/bobnis/conditum.png?branch=master)](https://travis-ci.org/bobnis/conditum)
- [latin for storehouse](http://translate.google.com/#la/en/conditum "conditum is latin for storehouse") (translate.google.com)

## A RESTful Non-Consumable In-App Purchase Manager
At a high level, conditum's main goal is to be the "Developer Server" in the following diagram:
![Store Transactions](http://i.imgur.com/UVjgar5.png "Store Transactions")
* figure from [In-App Purchase Programming Guide](http://developer.apple.com/library/ios/#documentation/NetworkingInternet/Conceptual/StoreKitGuide/APIOverview/OverviewoftheStoreKitAPI.html "In-App Purchase Programming Guide") (developer.apple.com)

## Responsibilities
- managing downloadable content routing
- facilitating transactions for non-consumable in-app purchases
- maintaining receipts for audit trails

## Built for the Cloud
* [AWS Elastic Beanstalk](http://aws.amazon.com/elasticbeanstalk/ "AWS Elastic Beanstalk") (aws.amazon.com) is the intended application host
 * [Amazon Elastic Compute Cloud (Amazon EC2)](http://aws.amazon.com/ec2/ "Amazon Elastic Compute Cloud (Amazon EC2)") (aws.amazon.com)
 * [Amazon Simple Storage Service (Amazon S3)](http://aws.amazon.com/s3/ "Amazon Simple Storage Service (Amazon S3)") (aws.amazon.com)
 * [Amazon Simple Notification Service (Amazon SNS)](http://aws.amazon.com/sns/ "Amazon Simple Notification Service (Amazon SNS)") (aws.amazon.com)
 * [Elastic Load Balancing](http://aws.amazon.com/elasticloadbalancing/ "Elastic Load Balancing") (aws.amazon.com)
 * [Auto Scaling](http://aws.amazon.com/autoscaling/ "Auto Scaling") (aws.amzon.com)
* [Amazon DynamoDB](http://aws.amazon.com/dynamodb/ "Amazon DynamoDB") (aws.amazon.com) is the intended data store
* [Amazon CloudFront](http://aws.amazon.com/cloudfront/ "Amazon CloudFront") (aws.amazon.com) is the intended content delivery network

## Quick Getting Started
1. Download and, if required, extract conditum
2. Run it
 * ```mvn clean jetty:run-war```
3. Review the documentation
 * [http://localhost:7777](http://localhost:7777 "Documentation") (test/test)
 * ```curl --request GET --user "test:test" http://localhost:7777 --verbose```
4. Inspect the running version
 * [http://localhost:7777/version](http://localhost:7777/version "Version") (test/test)
 * ```curl --request GET --user "test:test" http://localhost:7777/version --verbose```
5. Check to see if the application is running
 * [http://localhost:7777/ping](http://localhost:7777/ping "Ping") (no credentials required)
 * ```curl --request GET http://localhost:7777/ping --verbose```

# License
    Copyright 2013 Bobnis Innovations
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
