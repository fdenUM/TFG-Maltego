package aplicacion;

public class HTML extends Resource{

	public HTML(Object recurso) {
		super(recurso);
	}

	@Override
	public String toString() {
		return (String) recurso;
	}
	
}
