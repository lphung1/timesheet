
document.getElementById('logInButtonId').addEventListener('click', function(e){
        e.preventDefault(e); // stops expecting a new page/refresh
        user = {
            //-------model data for reference------
            // userId = r.getInt("userId");
            // userName = r.getString("userName");
            // password = r.getString("password");
            // firstName = r.getString("firstName");
            // lastName = r.getString("lastName");
            // role = r.getInt("roleId");
            // jobTitle = r.getString("jobTitle");

            userName: document.getElementById('userName').value,
            password: document.getElementById('password').value,
            userId: '',
            firstName: '',
            lastName: '',
            role: '',
            jobTitle: ''
            

        }



        let promise = axios.post('http://localhost:8080/timesheet-portal/api/user', user); //axios returns a promise
        // 1 callback for a successful response
        //another callback for a failed response
        console.log('Submit clicked');
        console.log(user.userName + " " + user.password);
        //window.location.replace('http://localhost:8080/timesheet-portal/home.html');
        //1 callback for a successful response
        promise.then(function(response){
            //200s

            
            console.log('then called');
            console.log('then called and response.username not null');
            
            //addName(response.data);

            //appendArtist(response.password);

            console.log('response name ' + response.userName);
            console.log('response p' + response.password);
            console.log('response data' + response.data);
            console.log('response data element' + response.data.userName);
            thisUser = response.data;


            document.getElementById('loginFormId').innerText = '';

            //document.getElementById('formHeader').innerText = 'Hello ' + thisUser.firstName;

            let getPromise = axios.get('http://localhost:8080/timesheet-portal/api/timesheets?userId=' + thisUser.userId);

            getPromise.then(function(response){
                //200s
                
                response.log;
                
                document.getElementById('timeSheetDivId').removeAttribute('hidden', true);
                fillTimeSheet(response.data);
                document.getElementById('formHeader').innerText = "Welcome " + thisUser.firstName;
        
            });
            //another callback for a failed response
            getPromise.catch(function(response){
                //400s or 500s
                console.log(response);
            }) ; 


            
  
        });
        //another callback for a failed response
        promise.catch(function(response){
            //400s or 500s
            console.log(response);
            alert("login not found in database");


        }) ; 


});

document.getElementById('timesheetButton').addEventListener('click', function(e){

    let promise = axios.get('http://localhost:8080/timesheet-portal/api/timesheets?userId=1');

    promise.then(function(response){
        //200s
        
        response.log;
        document.getElementById('timeSheetTableBody').innerText = '';

        fillTimeSheet(response.data);

    });
    //another callback for a failed response
    promise.catch(function(response){
        //400s or 500s
        console.log(response);
    }) ; 

    


});

function updateTable(uId){

    let promise = axios.get('http://localhost:8080/timesheet-portal/api/timesheets?userId=' + uId);

    promise.then(function(response){
        //200s
        
        response.log;
        document.getElementById('timeSheetTableBody').innerText = '';

        fillTimeSheet(response.data);

    });
    //another callback for a failed response
    promise.catch(function(response){
        //400s or 500s
        console.log(response);
    }) ; 



}



//on loaded page on home.html
document.addEventListener('DOMContentLoaded', function(e){

    document.getElementById('timeSheetDivId').setAttribute('hidden', true);




    


});




//adds usernamem to timesheet
function addName(user){


    // {
    //     "userName": "lphung1",
    //     "password": "pickles",
    //     "firstName": "Loi",
    //     "lastName": "Phung",
    //     "jobTitle": "Trainee",
    //     "userId": 1,
    //     "role": 1
    // }

    let fName = user.firstName;
    let lName = user.lastName;
    let uID = user.userId;

    getElementById('nameId').innerText = fName;


}


// "monHours": 3.0,
// "tueHours": 4.0,
// "wedHours": 5.0,
// "thuHours": 6.0,
// "friHours": 7.0,
// "satHours": 8.0,
// "sunHours": 2.0,
// "totalHours": 0.0,
// "timeSheetId": 2,
// "userId": 1,
// "statusId": 2,
// "weekEndDate": 1573362000000


//timesheet object
timeSheet = {
    timeSheetId: '',
    monHours: '',
    tueHours: '',
    wedhours: '',
    thuHours: '',
    friHours: '',
    satHours: '',
    sunHours: '',
    satusId: '',
    weekEndDate: ''

}

//appends and adds a timesheet object to the table
function appendTimesheet(timeSheet){

    let tr = document.createElement('tr');
    let monData = document.createElement('td');
    let tueData = document.createElement('td');
    let wedData = document.createElement('td');
    let thrData = document.createElement('td');
    let friData = document.createElement('td');
    let satData = document.createElement('td');
    let sunData = document.createElement('td');
    let statusData = document.createElement('td');
    let totalData = document.createElement('td');
    let dateData = document.createElement('td');

    monData.setAttribute('id', 'monData' + timeSheet.timeSheetId);
    tueData.setAttribute('id', timeSheet.timeSheetId);
    wedData.setAttribute('id', timeSheet.timeSheetId);
    thrData.setAttribute('id', timeSheet.timeSheetId);
    friData.setAttribute('id', timeSheet.timeSheetId);
    satData.setAttribute('id', timeSheet.timeSheetId);
    sunData.setAttribute('id', timeSheet.timeSheetId);
    statusData.setAttribute('id', timeSheet.timeSheetId);
    totalData.setAttribute('id', timeSheet.timeSheetId);
    dateData.setAttribute('id', timeSheet.timeSheetId);

    let editButton = document.createElement('button');
    let deleteButton = document.createElement('button');
    let submitButton = document.createElement('button');

    editButton.setAttribute('id', 'editButton' + timeSheet.timeSheetId );
    editButton.setAttribute('value', timeSheet.timeSheetId);

    deleteButton.setAttribute('id', 'deleteButton' + timeSheet.timeSheetId);
    deleteButton.setAttribute('value', timeSheet.timeSheetId);

    submitButton.setAttribute('id', 'timeSheet' + timeSheet.timeSheetId);
    submitButton.setAttribute('value', timeSheet.timeSheetId);
    
    console.log('edit button id' + editButton.id);


    monData.innerText = timeSheet.monHours;
    tueData.innerText = timeSheet.tueHours;
    wedData.innerText = timeSheet.wedHours;
    thrData.innerText = timeSheet.thuHours;
    friData.innerText = timeSheet.friHours;
    satData.innerText = timeSheet.satHours;
    sunData.innerText = timeSheet.sunHours;

    totalData.innerText = timeSheet.monHours + timeSheet.tueHours + timeSheet.wedHours + timeSheet.thuHours + timeSheet.friHours + 
    timeSheet.satHours + timeSheet.sunHours;

    statusData.innerText = setStatus(timeSheet.statusId);
    let dateFormat = new Date(timeSheet.weekEndDate);
    dateData.innerText = dateFormat.toLocaleDateString("en-US");

    editButton.innerText = 'Edit';
    deleteButton.innerText = 'Delete';
    submitButton.innerText = 'Submit';

    tr.appendChild(monData);
    tr.appendChild(tueData);
    tr.appendChild(wedData);
    tr.appendChild(thrData);
    tr.appendChild(friData);
    tr.appendChild(satData);
    tr.appendChild(sunData);
    tr.appendChild(totalData);
    tr.appendChild(statusData);
    tr.appendChild(dateData);

    if(statusData.innerText == 'Pending'){
        //console.log('trying to append buttons')
        tr.appendChild(editButton);
        //tr.appendChild(deleteButton); //enable when done testing
        tr.appendChild(submitButton);
    }  
    //comment out when done testing
    tr.appendChild(deleteButton); 
    

    document.getElementById('timeSheetTableBody').appendChild(tr);


}



document.getElementById('timeSheetTable').addEventListener('click', function(e){

    console.log('timesheet table element clicked: target' + e.target);
    console.log('value: ' + e.target.value);
    console.log('ID: ' + e.target.id + " Inner Text: " + e.target.innerText);

    //if submit butten pressed
    if(e.target.innerText == 'Submit'){
        let tsId = e.target.value;
        let apiUri = 'http://localhost:8080/timesheet-portal/api/timesheets?timesheetId=' + tsId;
        let getPromise = axios.get(apiUri);

        getPromise.then(function(response){
            //200s
            console.log(response.data);
            var thisTimeSheet = response.data;
            thisTimeSheet.statusId = '2';

            let updateStatusPromise = axios.put("http://localhost:8080/timesheet-portal/api/timesheets", thisTimeSheet);

            updateStatusPromise.then(function(updateResponse) {
                console.log("submit xhr successfull ");
                updateTable(thisUser.userId);

            });

        });

    }else if(e.target.innerText == 'Delete'){
        console.log("Delete clicked" + "e.target.value");

        let tsId = e.target.value;
        let apiUri = 'http://localhost:8080/timesheet-portal/api/timesheets?timesheetId=' + tsId;
        

        var deleteTimeSheetPromise = axios.delete(apiUri);

        deleteTimeSheetPromise.then(function(response){

            console.log("delete process requested");
            updateTable(thisUser.userId); //update table
        
         });
    }
    else if(e.target.innerText == 'Edit'){

        //e.target.innerText = 'Save';
        let thisTimesheetValue = e.target.value;
        console.log(thisTimesheetValue);

        document.getElement

        
    }
    else if(e.target.innerText = 'Add new Timesheet'){

        let d = endOfWeek(new Date());
        let dateString = d.toISOString().substring(0,10).replace('-', '').replace('-', '');
        
        
        let promise = axios.post('http://localhost:8080/timesheet-portal/api/timesheets?userId=' + thisUser.userId + "&date=" + dateString) ;

        promise.then(function(response){
            //200s
            
            response.log;
            document.getElementById('timeSheetTableBody').innerText = '';
    
            updateTable(thisUser.userId);
            response.status
    
        });
        //another callback for a failed response
        promise.catch(function(response){
            //400s or 500s
            console.log(response);
            alert("Week end date already exist, either delete and create a new one or wait until next week.");


        }) ; 
        




    }



});




//document.getelementbyId("name" +editButton.Value ).getattribute(value)

//fills the table with timesheets
function fillTimeSheet(list){

    for(let timeSheet of list){
        appendTimesheet(timeSheet);
    }

}

//sets status to pending or submitted based on status id
function setStatus (x){

    if(x == 2){
        return 'Submitted'
    }
    else{
        return 'Pending'
    }

}

function endOfWeek(date)
  {
     
    var lastday = date.getDate() - (date.getDay() - 1) + 6;
    return new Date(date.setDate(lastday));
 
  }



