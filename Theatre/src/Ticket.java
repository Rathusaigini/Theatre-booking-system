public class Ticket {
    int row;
    int seat;
    int price;
    Person person;

    public Ticket(Person person,int row,int seat,int price){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;

    }

    public void print(){
        System.out.println("Name:"+person.getName());
        System.out.println("SureName:"+person.getSurname());
        System.out.println("Email"+person.getEmail());
        System.out.println("Row"+row);
        System.out.println("Seat:"+seat);
        System.out.println("price:"+price);
        System.out.println(" ");
    }
}
