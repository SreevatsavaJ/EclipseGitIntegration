package DependsOn;

import org.testng.annotations.Test;

public class JavaEx2 {
  @Test(dependsOnGroups="Test2")
  public void t3() {
	  System.out.println("Test3");
  }
  @Test(dependsOnMethods="t3")
  public void t4() {
	  System.out.println("Test4");
  }
}
