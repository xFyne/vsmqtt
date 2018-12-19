import java.util.ArrayList;

public class DataSingleton{

    private static DataSingleton instance;

    //data
    private ArrayList<DataStruct> data;
    private ArrayList<Integer> ports;

    private DataSingleton () {
        data = new ArrayList<DataStruct>();
        ports = new ArrayList<Integer>();
    }

    public static synchronized DataSingleton getInstance () {
        if (DataSingleton.instance == null) {
                DataSingleton.instance = new DataSingleton ();
        }
        return DataSingleton.instance;
    }


    public synchronized ArrayList<DataStruct> getData() {
        return data;
    }

    public synchronized void setData(DataStruct data) {

        if(ports.contains(data.getPort())){

        }else{
            ports.add(data.getPort());
        }

        this.data.add(data);
    }

    public synchronized ArrayList<DataStruct> getCurrentData(){
        ArrayList<DataStruct> currentData = new ArrayList<>();
        for(int i = 0; i < ports.size(); i++){
            DataStruct dataStruct = new DataStruct();
            for(int j = 0; j < data.size(); j++){
                if(ports.get(i) == data.get(j).getPort()){
                    dataStruct = data.get(j);               //Letzter Eintrag mit selbem port finden
                }
            }
            currentData.add(dataStruct);
        }
        return currentData;
    }
}
