public class Task_1_Eiler
{
    public static void Do(double h, double x0, double b)
    {
        int k=0;

        double y0 = 2+Math.exp(1);
        double z0 = 1+Math.exp(1);

        Vector y = new Vector(2);
        y.Set(0, y0);
        y.Set(1, z0);

        double y_prec = y0;
        double epsilon = 0;
        System.out.println("k x y epsilon");
        for(double x=x0; x<=b; x+=h)
        {
            System.out.print(k + " " + x +  " " +  y.Get(0) +  " " + precise(x)+ " "+ epsilon + "\n");
            k++;
            //y+=h*f(x, y);
            y = Vector.Add(y, Vector.Mul(F(x, y), h));
            epsilon = Math.abs(precise(x) - y.Get(0));
        }
    }

    private static Vector F(double x, Vector yv)
    {
        Vector res = new Vector(2);

        double z = yv.Get(1);
        double y = yv.Get(0);

        res.Set(0, z);
        res.Set(1, ((x+1)*z - y) / x);

        return res;
    }

    private static double precise(double x)
    {
        return x + 1 + Math.exp(x);
    }



}
