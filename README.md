## What is Feature Toggle
Imagine you are building a new feature for your Java application. You want to test the feature with a small group of users before you roll it out to everyone. You could do this by creating two versions of your application, one with the new feature enabled and one with the new feature disabled. But this would require you to deploy two different versions of your application to production, which could be time-consuming and difficult.

Instead, you could use a **feature toggle**. A feature toggle is a variable that you can use to enable or disable a feature in your application at runtime. This means that you can deploy a single version of your application to production, and then use the feature toggle to control whether the new feature is enabled for different users.

## The Danny and Johny Problem
Imagine a situation **Danny** is the only daughter of **Big-B** for which Danny's father
is too protective of her. He never allowed Danny for party or any outing. He works in an hospital as a doctor on shift basis and its not certain when he will go to hospital and come back.  Danny has a boyfriend **Johny** who is too much partyholic  and he never gots time to chill-out with danny. So, together they found out a solution. They bribed a **Ramu Kaka** who runs tea stall in front of hospital, who will inform wheather Big-B is in hospital or left for hoe, so that in that time both Johny and Danny can party together.

Let's Understand chronology
>1. PAPA_HOME is the feature that **Johny** and **Danny** are dependent
>2. **Johny** will call **Danny** for party
>3. **Danny** will be keep looking for **Ramu Kaka**'s message.
>4. They will be decide if they will go for party or not based on **Ramu Kaka**'s message.


![Problem image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/lkhw3039btfqssuxnwpw.png)

##Solution
For the solution we have several library is there, I went with **FF4J**(Feature Flag for java). Below are the Code Snippet with Explanation.

##### 1. Head to [Spring Intializer](https://start.spring.io/) and create a starter spring boot project.
> I don't give a sh*t about which version you are using, But if you are using Spring Boot 3.x you have to use Java 17 or above. Gradle or Maven? I don't care. I went with Gradle.
##### 2. Inject the following line to your dependency for gradle.
`implementation 'org.ff4j:ff4j-core:2.0.0'`.
> If you are using maven, go to [Maven Central Repository](https://mvnrepository.com/artifact/org.ff4j/ff4j-core) and find your own dependency,

##### 3. Create A Java configuration something like FF4JConfig or whatever the f*ck you want.
> Where to create? This piece of sh*t is not for you.

##### 4. Add the following lines.

```
@Configuration //This piece of shit will tell spring boot that this is a configuration class
public class FF4jConfig {
    //Feature Name Definition
    public static final String PAPA_FEATURE = "PAPA_FEATURE";

    /*
    * FF4J Configuration
    * Create one bean of type FF4J. Why? Because concept of overriding is dying
    * Create of instance of FF4j. Obviously to use it further
    * Register your Feature
    * Enable or disable according to you
    * You are done, Thank me later (Code Below ⬇️)_
    */
    @Bean
    public FF4j ff4j(){
        FF4j ff4j = new FF4j();
        Feature papaFeature = new Feature(PAPA_FEATURE);
        papaFeature.disable(); //Default we will disable this feature later we will be enable
        ff4j.createFeature(papaFeature);
        return ff4j;
    }
}
```

##### 5. Define your API End point for Ramu Kaka and Johny to communicate.

```
    @GetMapping(path = "/should-we-party")
    public String shouldWeParty(){
        if(ff4j.check(FF4jConfig.PAPA_FEATURE))
            return "Yeah Johny Lets Go for party";
        else
            return "Sorry Johny, Papa is at Home";
    }

    /*
     * End-point will change the status of the feature
     * In our case it will notify johny about status of papa
     */
    @PostMapping(path = "/papa-at-hospital/{isAtHospital}")
    public String papaAtHospital(@PathVariable boolean isAtHospital){
        if(isAtHospital){
            ff4j.enable(FF4jConfig.PAPA_FEATURE);
            return ("Message deliverd to danny: PAPA IS AT HOSPITAL, GO PARTY");
        }
        else {
            ff4j.disable(FF4jConfig.PAPA_FEATURE);
            return ("Message deliverd to danny: PAPA LEFT HOSPITAL, COME BACK");
        }
    }
```

>Don't forget to autowire the FF4j to instantiate before using

```
@Autowired
    FF4j ff4j;

```

### Results
Following is the endpoint for **Johny** to ask **Danny** out.

![should-we-party endo point](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/b2ftr3ogge61z60vn4xu.jpg)

Following is the endpoint for **Ramu Kaka** to ask **Danny** out.

![papa-at-hospital](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/1nhv92b3jmgy0btkbwsg.png)

Once **Ramu Kaka** informed **Danny** that **Big-B** is in hospital, **Johny** will get the following response on calling **Danny**.

![Success Response](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/5imbh91fgpu96e4okut3.png)

After getting this response, Johny and Danny went for party. And the rest of the story **cleared from browser history**




##Thank you





