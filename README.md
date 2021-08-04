# Pokemon Shop

## Project Description

Online Pokemon shop where visitors can sign in using OAuth via Google and GitHub, make transactions via Stripe in exchange for in-store tokens, which can be used to buy contents from our website. Other features include daily raffle, reward system based on user sign in daily, and weather API based on visitor location.

## Technologies Used

* Angular12
* Webpack
* MaterialUI
* Spring MVC
* Spring Data
* Spring Security(JWT)
* Hibernate
* Lombok
* Log4J
* JUnit
* Docker
* OAuth(Google, GitHub)
* Payment(Stripe API)

## Features

* visitor can sign in using their existing Google or GitHub account
* visitor can purchase their in-store tokens via Stripe Payment API
* visitor can spend their tokens on store contents
* visitor can join daily raffle for free tokens
* visitor can get free tokens on signing in daily

To-do list:
* Better home page
* Better footer component to display company missions

## Getting Started

```bash
  $ git clone https://github.com/JW406/Pokemon-Shop.git
  $ cd Pokemon-Shop/client && yarn && yarn ng serve
  $ export rds_url=YOUR_RDS_URL
  $ export rds_username=YOUR_RDS_USERNAME
  $ export rds_pwd=YOUR_RDS_PWD
  $ export proj02_google_client_id=YOUR_ID/SECERT
  $ export proj02_google_client_secret=YOUR_ID/SECERT
  $ export proj02_github_client_id=YOUR_ID/SECERT
  $ export proj02_github_client_secret=YOUR_ID/SECERT
  $ export proj02_stripe_client_id=YOUR_ID/SECERT
  $ export proj02_stripe_client_secret=YOUR_ID/SECERT
  $ cd ../
  $ mvn package -f ./pom.xml && mv target/*.war target/ROOT.war
  $ unzip ./target/ROOT.war -d $CATALINA_BASE/webapps/ROOT
  $ sh $CATALINA_BASE/bin/startup.sh
```

## Usage
![image](https://i.ibb.co/5rZmCJz/project02-01.png)
![image](https://i.ibb.co/1b9HVCd/project02-02.png)
![image](https://i.ibb.co/H2N0WQf/project02-03.png)
![image](https://i.ibb.co/HGw9Lbv/project02-04.png)
![image](https://i.ibb.co/xzjVmYw/project02-05.png)
![image](https://i.ibb.co/Nm9VzJ9/project02-06.png)
![image](https://i.ibb.co/M87BQB1/project02-07.png)

## Contributors

[@arrMan-dev](https://github.com/arrMan-dev)
[@mafifi-eng](https://github.com/mafifi-eng)
[@sean524](https://github.com/sean524)

## License

This project uses the following license: [MIT](https://opensource.org/licenses/MIT)
