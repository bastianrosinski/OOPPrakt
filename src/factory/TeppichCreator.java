package factory;

public class TeppichCreator extends Creator{

	@Override
	public Product factoryMethod() {
		return new TeppichProduct();
	}

}
