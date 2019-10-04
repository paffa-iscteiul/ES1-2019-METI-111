
public class Marco {
	public static int Vida;
	public static int Stamina ;
	public static int Strengt ;
	public static int Atack ;
	
	public static void Marco (int vida, int stamina, int strengt) {
		Vida=vida;
		Stamina=stamina;
		Strengt=strengt;
		
	}
	public static int getVida() {
		return Vida;
	}
	
	public static int getStamina() {
		return Stamina;
	}
	
	public static int getStrengt() {
		return Strengt;
	}
	
	public static int getAtack() {
		return Atack;
	}
	public static void Atack_calcule() {
		Atack = (Strengt * Stamina)/Vida ;
		System.out.println("A vida do marco inicialmente é :");
		System.out.println( Atack);
	}
	//Nao me lembro como testar
	
	public static void main(String[] args) {

      Marco(100,175,300);
      Atack_calcule();
      getVida();
      getStamina();
      getStrengt();
      getAtack();

        }
}
