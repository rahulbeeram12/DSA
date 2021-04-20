public class dynamicStack extends stack{
    public dynamicStack(){
        super();
    }

    public dynamicStack(int size){
        super(size);
    }

    public dynamicStack(int arr[]){
        int n = arr.length;
        super.initialize(2 * n);

        for(int ele : arr)
            super.push_(ele);
    }

    @Override
    public void push(int data) throws Exception{
        if(super.size() == super.capacity()){
            int n = super.size();
            int temp[] = new int[n];
            
            int i = super.size() - 1;
            while(super.size() > 0){
                temp[i--] = super.pop_();
            }

            super.initialize(2 * n);

            while(i < n){
                super.push_(temp[i++]);
            }            
        }
        
        super.push(data);
    }
}