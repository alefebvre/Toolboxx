package data;

import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;


public class DataEngine {
	
	private static DataEngine instance;
	
	private MpgMap mapMapper;
	private MpgInfos newsMapper;
	private MpgPoi poiMapper;
	private MpgRoute routeMapper;
	
	private DataEngine()
	{
		mapMapper = new MpgMap();
		newsMapper = new MpgInfos();
		poiMapper = new MpgPoi();
		routeMapper = new MpgRoute();
	}
	
	public static DataEngine getInstance()
	{
		if(instance == null)
			instance = new DataEngine();
		return instance;
	}
	
	public Boolean persist(Identifiable o)
	{
		Boolean r = false;
		PreparedStatement query;
		if(o.getId() > 0)
		{
			switch(o.getClass().getName())
			{
				case "data.Map":
					query = Cad.getInstance().getPreparedStatement(mapMapper.upd());
					break;
					
				case "data.Route":
					query = Cad.getInstance().getPreparedStatement(routeMapper.upd());
					break;
					
				case "data.New":
					query = Cad.getInstance().getPreparedStatement(newsMapper.upd());
					break;
					
				case "data.Poi":
					query = Cad.getInstance().getPreparedStatement(poiMapper.upd());
					break;
					
				default:
					System.out.println(String.format("Unable to persist : class %s is unknown in persist method range.", o.getClass().getName()));
			}	
			r=true;
		}
		else
		{
			switch(o.getClass().getName())
			{
				case "data.Map":
					r = Cad.getInstance().doSQL(mapMapper.add(o));
					break;
					
				case "data.Route":
					r = Cad.getInstance().doSQL(routeMapper.add(o));
					break;
					
				case "data.New":
					r = Cad.getInstance().doSQL(newsMapper.add(o));
					break;
					
				case "data.Poi":
					r = Cad.getInstance().doSQL(poiMapper.add(o));
					break;
					
				default:
					System.out.println(String.format("Unable to persist : class %s is unknown in persist method range.", o.getClass().getName()));
			}	
		}
		return r;
	}

	public ArrayList<Identifiable> Load(MapperEnum table)
	{
		ResultSet rs;
		switch(table)
		{
			case MAP:
					rs = Cad.getInstance().getSQL(mapMapper.getAll());
					return IdentifiableFactory.createIdentifiable(table, rs);
		}
		return null;
		
	}
}
