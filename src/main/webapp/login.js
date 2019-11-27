
document.getElementById('logInButtonId').addEventListener('click', function(e){
        e.preventDefault(e); // stops expecting a new page/refresh
        let user = {

            userName: document.getElementById('userName').value,
            password: document.getElementById('password').value
            

        }

        let promise = axios.post('http://localhost:8080/timesheet-portal/api/user', user); //axios returns a promise
        // 1 callback for a successful response
        //another callback for a failed response
        console.log('Submit clicked');
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
            
            let b = document.createElement('h1');
            b.innerText = 'Login Good';
            document.getElementById('title').appendChild(b);


            
  
        });
        //another callback for a failed response
        promise.catch(function(response){
            //400s or 500s
            console.log(response);
        }) ; 


});

document.getElementById('timesheetButton').addEventListener('click', function(e){

    let promise = axios.get('http://localhost:8080/timesheet-portal/api/timesheets');

    promise.then(function(response){
        //200s
        response.log;
        fillTimeSheet(response.data);

    });
    //another callback for a failed response
    promise.catch(function(response){
        //400s or 500s
        console.log(response);
    }) ; 

    


});



//on loaded page on home.html
document.addEventListener('DOMContentLoaded', function(e){


    


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

    tr.appendChild(deleteButton); 
    

    document.getElementById('timeSheetTable').appendChild(tr);


}

// document.getElementById('editButton').addEventListener('click',function(e){


// } );

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

            });

        });

    }else if(e.target.innerText == 'Delete'){
        console.log("Delete clicked" + "e.target.value");

        let tsId = e.target.value;
        let apiUri = 'http://localhost:8080/timesheet-portal/api/timesheets?timesheetId=' + tsId;
        

        var deleteTimeSheetPromise = axios.delete(apiUri);

        deleteTimeSheetPromise.then(function(response){

            console.log("delete process requested");
            
        
         });
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

