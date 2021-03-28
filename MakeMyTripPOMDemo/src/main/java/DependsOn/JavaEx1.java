package DependsOn;

import org.testng.annotations.Test;

public class JavaEx1 {
  @Test
  public void t1() {
	  System.out.println("Test1");
  }
  @Test(dependsOnMethods="t1",groups={"Test2"})
  public void t2() {
	  System.out.println("Test2");
  }
}
