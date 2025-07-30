
fetch("http://localhost:8080/users",).then((e) => {
    if(e.ok){
        let users=e.body.content
        console.log(users)
    }
})