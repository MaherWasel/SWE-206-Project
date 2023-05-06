package com.example;

class Team{
    private String name;
    private int numOfmemebers;
    //the members is arraylist becuse it is easier to add an remove from it 
                    //need to be changed in the class diagram
    private ArrayList<Student> mempers;

    Team(String name, int num) throws Exception{
        if (num <=0)
            throw new Exception("number of members can not be less than one");
        
        this.name=name;
        this.numOfmemebers=num;
        mempers=new ArrayList<Student>();
    }

    public void addMembers(Student x) throws Exception{
        if (mempers.size()<numOfmemebers){
            mempers.add(x);
        }    
        else{
            throw new Exception("the team is full already");
        }
}

public void removeMember(String x)throws Exception{
    if (mempers.size()>0){
        if(!mempers.remove(x))
        throw new Exception("there is no student with this name");
    }
    else
        throw new Exception("the team is empty");
}

        //need to be added to the class diagram
//the method will retrun true if they have the same member on both teams
public boolean noMembersConflect(Team other){
    ArrayList<Student> thisList=this.getMempers();
    ArrayList<Student> otherList=other.getMempers();
//  the following will only keep the comman elements between the lists
    thisList.retainAll(otherList);

    return (thisList.isEmpty());
}



    public ArrayList<Student> getMempers() {
        return (ArrayList<Student>) mempers.clone();
    }

    @Override
    public String toString() {
        return this.name;
    }


    
}
