package JavaProject.hotel;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HotelHandler extends DefaultHandler{
   Vector<Hotel> hotelList;
   boolean isPlaces;
   boolean isPlace;
   boolean isHotelName;
   boolean isArea;
   boolean isHotelType;
   boolean isPrice;
   
   Hotel hotel;
   

   public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
      if(tag.equals("places")) {
         isPlaces=true;
         hotelList=new Vector<Hotel>();
      }else if(tag.equals("place")) {
         isPlace=true;
         hotel=new Hotel();
      }else if(tag.equals("hotelName")) {
         isHotelName=true;
      }else if(tag.equals("area")) {
         isArea=true;
      }else if(tag.equals("hotelType")) {
         isHotelType=true;
      }else if(tag.equals("price")) {
         isPrice=true;
      }
   }
   

   public void characters(char[] ch, int start, int length) throws SAXException {
      String data=new String(ch,start,length);
      if(isHotelName) {
         hotel.setHotelName(data);
      }else if(isArea){
         hotel.setArea(data);
      }else if(isHotelType){
         hotel.setHotelType(data);
      }else if(isPrice){
         hotel.setPrice(Integer.parseInt(data));
      }
   }
   

   public void endElement(String uri, String localName, String tag) throws SAXException {
      if(tag.equals("hotelName")) {
         isHotelName=false;
      }else if(tag.equals("area")) {
         isArea=false;
      }else if(tag.equals("hotelType")) {
         isHotelType=false;
      }else if(tag.equals("price")) {
         isPrice=false;
      }else if(tag.equals("place")) {
         isPlaces=false;
         hotelList.add(hotel);
      }else if(tag.equals("places")) {
         isPlace=false;
      
      }
   }
   
}