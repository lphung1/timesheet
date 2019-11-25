document.getElementById('logInButtonId').addEventListener('click', function(e){
        e.preventDefault(); // stops expecting a new page/refresh
        let user = {

            userName: document.getElementById('userName').value,
            password: document.getElementById('password').value

        }

        let promise = axios.post('http://localhost:8080/timesheet-portal/api/user', user); //axios returns a promise
        // 1 callback for a successful response
        //another callback for a failed response
        console.log('Submit clicked');
        //1 callback for a successful response
        promise.then(function(response){
            //200s
            if(response.userName != null){
            window.location.replace('http://localhost:8080/timesheet-portal/home.html');
            //appendArtist(response.password);
            console.log(response);
                console.log('error fetching user');
            }
            console.log(response.userName);
            console.log(response.password);
            
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