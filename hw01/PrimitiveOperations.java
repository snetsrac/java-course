public class PrimitiveOperations {
  public static void main(String[] args) {
    int intVariable = 42;
    System.out.println(intVariable);

    double doubleVariable = 3.14159;
    System.out.println(doubleVariable);

    double product = intVariable * doubleVariable;
    System.out.println(product);

    double castedIntVariable = (double) intVariable;
    System.out.println(castedIntVariable);

    int castedDoubleVariable = (int) doubleVariable;
    System.out.println(castedDoubleVariable);

    char charVariable = 'C';
    System.out.println(charVariable);

    char lowerCharVariable = (char) ((int) charVariable + 32);
    System.out.println(lowerCharVariable);
  }
}
