let index = {
    init:function(){
        $("#btn-save").on("click",()=>{
            this.save();
        })
    },
    save:function(){
        let data ={
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        }
        console.log(data);
    }
}
index.init();