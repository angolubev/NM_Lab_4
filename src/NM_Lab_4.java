public class NM_Lab_4
{
    public static void main(String[] args)
    {
        double h1=0.1;
        double a1=1;
        double b1 =2;
        double x01 =1;

        //Task_1_Eiler.Do(h1, x01, b1);
        //Task_1_RungeKut.Do(h1, x01, b1);
        //Task_1_Adams.Do(h1, x01, b1);

        double h2 =0.1;
        double a =0, b=1;
        double ya = -2, yb = -1.5;
        double alpha =1, beta =0, gamma =0, delta =1;
        double epsilon = 0.0001;

        /*double h2 =0.1;
        double a =0, b=1;
        double ya = 0, yb = -0.75;
        double alpha =0, beta =1, gamma =1, delta =1;
        double epsilon = 0.00001;*/

        //Task_2_Strelba.Do(h2, a, b, ya, yb, epsilon, alpha, beta, gamma, delta);
        Task_2_Rasnost.Do(h2, a, b, alpha, beta, gamma, delta, ya, yb);

    }
}
