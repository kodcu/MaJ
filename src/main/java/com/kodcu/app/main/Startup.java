package com.kodcu.app.main;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import com.kodcu.app.data.Brand;
import com.kodcu.app.data.Data;
import com.kodcu.app.devices.Device;
import com.kodcu.app.devices.NoteBook;
import com.kodcu.app.devices.Tablet;
/**
 * 
 * @author HAKAN
 *
 */
public class Startup {  

	public static void main(String[] args) { 
		
		Data data = new Data();
		System.out.println("The lenght of the tablet list : " + data.getTabletslength());
		
		List<Device> tablets =  data.getPhonesAsList();
		System.out.println("## Display all the phones ##");
		tablets.forEach(t -> {
			System.out.println(t.deviceBrand() +"\t::"+t.deviceType());
		});
		System.out.println("## Don't display phones being an unknown brand ##");
		data.getPhonesAsStream().filter(p -> !p.deviceBrand().equals(Brand.Unknown))
								.forEach(p -> System.out.println(p.deviceBrand() +"\t::"+p.deviceType()));
		
		System.out.println("## Match any Asus notebook, if present return true, otherwise false ##");
		Stream<NoteBook> streamNoteBook = data.getNoteBooksAsStream();
		boolean included = streamNoteBook.anyMatch(n -> n.deviceBrand().equals(Brand.Asus));
		System.out.println(included);
		
		System.out.println("## Show non-null tablet brands ##");
		data.getTabletsAsStream().forEach(t->{
			Optional<Tablet> table = Optional.ofNullable(t);
			table.ifPresent(p->{
				System.out.println(p.deviceBrand());
			});
		});
		
		System.out.println("## Show only non-null Samsung and Huawei devices");
		data.getAllDataAsStream().forEach(dev -> {
			Optional<Device> device = Optional.ofNullable(dev);
			device.filter(f -> f.deviceBrand().equals(Brand.Samsung) || f.deviceBrand().equals(Brand.Huawei))
				  .ifPresent(Startup::showBeautyMessage);
		});	
		
	} 
	
	private static void showBeautyMessage(Device d){
		System.out.println("<"+d.deviceBrand()+">\t:: ("+d.deviceType()+")");
	}
}
