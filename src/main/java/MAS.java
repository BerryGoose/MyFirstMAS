package MAS;
import Geometry.*;


class Route{
    Point start;
    Point end;
    double length;
    double direction;
    
    int[] st_time = new int[2];
    double cost;
    int end_time;
    String type;
    Order order;
}

public class Courier{
    String disp_addr;
    Route[] routes;
    Point position;
    double direction;
    int time;
    double earned;
    String status;
    double satisfaction;
}

public class Order{
    String disp_addr;
    Point start;
    Point end;
    int[] st_time = new int[2];
    int[] end_time = new int[2];
    double max_cost;
}

public class Dispatcher{
    Courier[] couriers;
    Order[] orders;
    int time;
    double earned;
    Order[] wait_list;
}