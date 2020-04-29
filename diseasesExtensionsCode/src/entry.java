
class Entry {
	private String _code;
	private String _description;



	public Entry(String code, String description){
		_code = code;
		_description = description;
	}
	
	public String getCode(){
		return _code;
	}

	public String getDescription(){
		return _description;
	}
}