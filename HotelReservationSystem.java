package package1;
import java.util.*;

class Reservation
{
	int reservationId;
    String name;
    String roomCategory;
    int noOfRooms;
    int mobile;
    String status;
}
class HotelBooking{
	private int noOfStandard=5;
	private int noOfDeluxe=8;
	private int noOfSuite=3;
	private int reservation=1000;
	
	private List<Reservation> history=new ArrayList<>();
	
	void rooms()
	{
		System.out.println("               ");
		System.out.println("------Available Rooms------");
		System.out.println("Standard : "+this.noOfStandard);
		System.out.println("Deluxe   : "+this.noOfDeluxe);
		System.out.println("Suite    : "+this.noOfSuite);
	}
	
	void booking(String roomname,int noofroom,String name,int mobilenum,String payment)
	{
		if(roomname.equals("Deluxe") && noofroom<=this.noOfDeluxe||roomname.equals("Suite") && noofroom<=this.noOfSuite||roomname.equals("Standard") && noofroom<=this.noOfStandard)
		{
			this.reservation++;
			Reservation r = new Reservation();
			r.reservationId = this.reservation;
			r.name = name;
			r.roomCategory = roomname;
			r.noOfRooms = noofroom;
			r.mobile = mobilenum;
			r.status = "Active";

			history.add(r);
		if(payment.equals("yes"))
		{
		System.out.println("                     ");
		System.out.println("Payment Successful ✔");
		System.out.println("Booking Confirmed");
		System.out.println("Reservation Id:R"+this.reservation);
		System.out.println("Name:"+name);
		System.out.println("Room:"+roomname);
		System.out.println("Status:Active");
		}
		else
		{
			System.out.println("                     ");
			System.out.println("Payment Cancelled ❌");
			System.out.println("Booking Failed");
		}
		}
		else
		{
			System.out.println("                 ");
			System.out.println("Not enough rooms available! Please try again.");
		}
	}
	
	void bookedroom(String name,int noofroom)
	{
		if(name.equals("Deluxe"))
		{
			this.noOfDeluxe-=noofroom;
		}
		else if(name.equals("Standard"))
		{
			this.noOfStandard-=noofroom;
		}
		else if(name.equals("Suite"))
		{
			this.noOfSuite-=noofroom;
		}
	}
	
	void viewreservation()
	{
		if(history.isEmpty())
		{
			System.out.println("No Bookings Yet !!");
		}
		else
		{
			for(Reservation r:history)
			{
				System.out.println("Reservation Id: R" + r.reservationId +
                        " | Name: " + r.name +
                        " | Room: " + r.roomCategory +
                        " | Rooms: " + r.noOfRooms +
                        " | Status: " + r.status);
			}
		}
	}
	
	void roomcancel(int id)
	{
		boolean found =false;
		for(Reservation r:history)
		{
			if(r.reservationId==id&&r.status.equals("Active"))
			{
				r.status="Cancelled";
				
				if (r.roomCategory.equals("Deluxe")) 
				{
	                this.noOfDeluxe += r.noOfRooms;
	                
	            } else if (r.roomCategory.equals("Standard"))
	            {
	                this.noOfStandard += r.noOfRooms;
	                
	            } else if (r.roomCategory.equals("Suite")) 
	            {
	                this.noOfSuite += r.noOfRooms;
	            }
				System.out.println("                                        ");
				System.out.println("Reservation Id Cancelled Successfully:R"+r.reservationId);
				System.out.println("Refund Processed (simulation) ✔");
				found=true;
				break;
			}
		}
			if(!found)
			{
				System.out.println("No Active Reservation Found With Id: R" + id);
			}
		
	}
}
public class HotelReservationSystem 
{
	public static void main(String[]args)
	{
		System.out.println("Enter Your Choice:");
		System.out.println("1. View Available Rooms");
		System.out.println("2. Book a Room");
		System.out.println("3. Cancel Reservation");
		System.out.println("4. View Booking Details");
		System.out.println("5. Exit");
		
		HotelBooking obj=new HotelBooking();
		boolean run=true;
		Scanner input=new Scanner(System.in);
		while(run)
		{
		System.out.println("               ");
		System.out.print("Choose:");
		int Choose=input.nextInt();
		input.nextLine();
		
		if(Choose==1)
		{
			obj.rooms();
		}
		else if(Choose==2)
		{
			System.out.print("Enter Room Category (Standard/Deluxe/Suite):");
			String roomname=input.nextLine();
			if(roomname.equals("Deluxe")||roomname.equals("Standard")||roomname.equals("Suite"))
			{
			System.out.print("Enter The No Of Rooms:");
			int noofroom=input.nextInt();
			if(noofroom>0)
			{
			input.nextLine();
			System.out.print("Enter Name:");
			String name=input.nextLine();
			System.out.print("Enter Mobile_Number:");
			int mobilenum=input.nextInt();
			input.nextLine();
			System.out.print("Proceed with payment? (yes/no):");
			String payment=input.nextLine();
			obj.booking(roomname,noofroom,name,mobilenum,payment);
			obj.bookedroom(roomname,noofroom);
			}
			else
			{
				System.out.println("               ");
				System.out.println("Enter Valid No Of Rooms");
			}
			}
			else
			{
				System.out.println("                     ");
				System.out.println("Invalid Room Category! Please choose Standard, Deluxe, or Suite.");
			}
		}
		else if(Choose==3)
		{
			System.out.print("Enter The Id To Cancel:");
			int cancelroom=input.nextInt();
			input.nextLine();
			obj.roomcancel(cancelroom);
		}
		else if(Choose==4)
		{
			obj.viewreservation();
		}
		else if(Choose==5)
		{
			System.out.println("               ");
			System.out.print("Exits.....");
			run=false;
		}
		}
	}
}
