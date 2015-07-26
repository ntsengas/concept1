package concept1.web.domain;

public class BeanA {

	private BeanB beanB;

	public BeanB getBeanB() {
		return beanB;
	}
	
	public void execute() {
		System.out.println(this.beanB.getBeanC().getMessage());
	}

	public void setBeanB(BeanB beanB) {
		this.beanB = beanB;
	}

	public BeanA(BeanB beanB) {
		this.beanB = beanB;
	}

}
