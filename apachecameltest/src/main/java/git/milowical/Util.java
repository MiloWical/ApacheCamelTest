package git.milowical;

public class Util {
  
  public static void printStackTrace(Throwable t) {
    System.out.println(t);
    for (StackTraceElement ste : t.getStackTrace()) {
      System.out.println("\tat " + ste);
    }
    Throwable cause = t.getCause();
    if (cause != null) {
      System.out.print("Caused by ");
      printStackTrace(cause);
    }
  }
}
