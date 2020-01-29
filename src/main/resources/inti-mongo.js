db.createUser(
    {
        user : "USERNAME",
        pwd : "PWD",
        roles : [
            {
                role : "readWrite",
                db : "DB name"
            }
        ]
    }
)