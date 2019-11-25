
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


function fillTableWithTimesheets(list){

    for(let artist of list){
        appendTimesheet(TimeSheet);
    }


};

function appendTimesheet(TimeSheet){


};

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


