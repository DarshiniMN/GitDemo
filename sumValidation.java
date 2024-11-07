package demoproject;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class sumValidation {
@Test
public void sumOfCourses() {
	int price,no_of_copies,amount,sum=0,i;
	JsonPath js1=new JsonPath(payload.CoursePrice());
	int count=js1.getInt("courses.size()");
	int totalAmount=js1.getInt("dashboard.purchaseAmount");
	for( i=0;i<count;i++) {
	price=js1.getInt("courses["+i+"].price");
	no_of_copies=js1.getInt("courses["+i+"].copies");
	amount=no_of_copies*price;
	sum=sum+amount;
     }
System.out.println(totalAmount);
System.out.println(sum);
Assert.assertEquals(totalAmount, sum);
}
}
