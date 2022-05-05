class IFExample {
    public static void main(String[] args) {
        int i=1;
        int x=10;
        do{
            System.out.println(i);
            i++;
            if(x>20)
                System.out.println(x);
            else
                System.out.println(x+5);
        }while(i<=10);

        for(int j=0;j<4;j++)
        System.out.println(j);

        while(x>20)
            System.out.println(x);
    }
}