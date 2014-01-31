
public class ValidNumber {

	
	public static void main(String[] args){
		ValidNumber v = new ValidNumber();
		String s = "+42e+76125";
		System.out.print(v.isNumber(s));
	}
    public boolean isNumber(String s) {
    	if(s==null || s.length()==0 || s.charAt(0)=='e' || s.charAt(s.length()-1)=='e') return false;
    	
        s = s.trim();
        if(s.length()==0 || s.charAt(0)=='e' || s.charAt(s.length()-1)=='e') return false;
        
        int len = s.length();
        String newS = s.replace("-", "");
        if(newS.length()==0 || len-newS.length()>1) return false;
        if(len-newS.length()==1){
        	String[] a = s.split("\\-");
        	String[] b = s.split("e");
        	if(!(s.charAt(0)=='-' || (a[0].length()-b[0].length()==1)))
        		return false; 		
        }
        s = newS;
        
        len = s.length();
        newS = s.replace("+", "");
        if(newS.length()==0 || len-newS.length()>1) return false;
        if(len-newS.length()<3){
        	String[] a = s.split("\\+");
        	String[] b = s.split("e");
        	if(!(s.charAt(0)=='+' || (a[0].length()-b[0].length()==1)))
        		return false; 		
        }
        s = newS;
        
        len = s.length();
        newS = s.replace(".", "");
        if(newS.length()==0 || len-newS.length()>1 || newS.charAt(0)=='e' || newS.charAt(newS.length()-1)=='e') return false;
        if(len-newS.length()==1){
        	String[] a = s.split("\\.");
        	String[] b = s.split("e");
        	if(a[0].length()>b[0].length())
        		return false;     	
        }
        s = newS;      
        
        len = s.length(); 
        s = s.replace("e", "");       
        if(s.length()==0 || len-s.length()>1 || s.charAt(0)=='e' || s.charAt(s.length()-1)=='e') return false;
        
        return s.matches("[0-9]+");    
    }
}
