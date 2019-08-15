package demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
 
@RestController
public class HelloWorldController {
 
    @GetMapping("/hello")
    public String sayHello() {
    	String topicArn = "arn:aws:sns:eu-west-1:383485234595:test";
        String awsAccount = "AKIAVSSMAJWR74UIQFWL";
        String awsSecret = "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";
        String region = "EU_WEST_1";
//        Region re = new Region("eu-west-1", "EU (Ireland)");
//        Regions region1 = new Regions("eu-west-1", "EU (Ireland)");
        AmazonSNSClient snsClient = new AmazonSNSClient(new BasicAWSCredentials(awsAccount, awsSecret));
        snsClient.setRegion(Region.getRegion(Regions.fromName("eu-west-1")));


        try {
            PublishRequest publishReq = new PublishRequest()
                    .withTopicArn(topicArn)
                    .withMessage("HELLO");
            snsClient.publish(publishReq);

        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Cannot send notification to SNS service", e);
        	System.out.println("exception"+e.toString());
        } finally {
//            LOGGER.log(Level.INFO, "Webhook runner terminated");
        }
    	return "Hello Java Code Geeks!";
    }
}