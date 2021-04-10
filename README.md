# Demo-Firebase-Realtime-Database

## 使用雲端資料庫即時讀取

### 使用Android studio 導入步驟：
- tools -> firebase
- Realtime Database
- connect to firebase
- Add the readltime datebase to you app

### firebase console
- 設定規則

```{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```

- 資料
```
fcmdemo-6d4b6
message: "qqq" 資料
message2: "qqq" 資料
```

### Android studio 

```
private var mDatabase: FirebaseDatabase = FirebaseDatabase.getInstance() // 初始化
private lateinit var myRef: DatabaseReference // 資料
private lateinit var myRef2: DatabaseReference


 private fun setWrite() {
        myRef = mDatabase.getReference("message")
        myRef2 = mDatabase.getReference("message2")
       
        myRef.setValue(editText.text.toString())
        myRef2.setValue(editText.text.toString())
    }
    
 
 private fun getReadData() {  // addValueEventListener 監聽變化
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value."+  error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                tvData.text = value
            }

        })
    }    
```

