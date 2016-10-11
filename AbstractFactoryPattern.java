
abstract class AbstractFactory {
  public abstract ProductA createProductA();
  public abstract ProductB createProductB();
}

class ConcreteFactory1 extends AbstractFactory {
  public ProductA createProductA() {
    return (new ProductAX());
  }
  public ProductB createProductB() {
    return (new ProductBX());
  }
}

class ConcreteFactory2 extends AbstractFactory {
  public ProductA createProductA() {
    return (new ProductAY());
  }
  public ProductB createProductB() {
    return (new ProductBY());
  }
}

class Client {
  public static void main(String args[]) {
    AbstractFactory factory = new ConcreteFactory1();
    ProductA producta = factory.createProductA();
    producta.name();
    producta.type();
    ProductB productb = factory.createProductB();
    productb.name();
    productb.size();
  }
}

abstract class ProductA {
  public abstract void name();
  public abstract void type();
}

class ProductAX extends ProductA {
  public void name() {
    System.out.println("ProductAX");
  }
  public void type() {
    System.out.println("type: ProductAX");
  }
}

class ProductAY extends ProductA {
  public void name() {
    System.out.println("ProductAY");
  }
  public void type() {
    System.out.println("type: ProductAY");
  }
}

abstract class ProductB {
  public abstract void name();
  public abstract void size();
}

class ProductBX extends ProductB {
  public void name() {
    System.out.println("ProductBX");
  }
  public void size() {
    System.out.println("B1 size");
  }
}

class ProductBY extends ProductB {
  public void name() {
    System.out.println("ProductBY");
  }
  public void size() {
    System.out.println("B2 size");
  }
}
