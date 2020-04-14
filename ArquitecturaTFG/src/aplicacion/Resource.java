package aplicacion;

public abstract class Resource {
	
	protected Object recurso;
	
	public Resource (Object recurso) {
		this.recurso = recurso;
	}
	
	public abstract String toString();
	
}
