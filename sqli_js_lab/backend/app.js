const express = require('express');
const { Pool } = require('pg');
const app = express();
const PORT = 3000;

app.use(express.json());

const db = new Pool({
  host: process.env.DB_HOST || 'localhost',
  user: process.env.DB_USER || 'task2_user',
  password: process.env.DB_PASSWORD || 'task2_password',
  database: process.env.DB_NAME || 'task2',
  port: process.env.DB_PORT || 5432,
});


db.connect();

const path = require('path');

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});


app.get('/get-all', (req, res) => {
  db.query('SELECT * FROM drink ORDER BY id;', (err, result) => {
    if (err) {
      console.error("Error fetching drinks:", err);
      return res.status(500).send("Database error");
    }
    res.json(result.rows);
  });
});


// curl -X POST http://localhost:3000/dynamic-procedure \
//   -H "Content-Type: application/json" \
//   -d "{\"input\": \"Latte'; DROP TABLE drink;--\"}"
app.post('/dynamic-procedure', (req, res) => {
  const userInput = req.body.input;
  const query = 'CALL get_one_drink_vulnerable($1);';
  console.log("Executing query:", query); //debug

  db.query(query, [userInput], (err, result) => {
    res.send("Procedure executed");
  });
});

// "{\"input\": \"'); DROP TABLE drink; --\"}"
app.post('/unsafe-call', (req, res) => {
  const userInput = req.body.input;
  const query = `CALL get_one_drink('${userInput}');`;
  console.log("Executing query:", query); //debug
  db.query(query, (err, result) => {
    res.send("Procedure called.");
  });
});


app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
