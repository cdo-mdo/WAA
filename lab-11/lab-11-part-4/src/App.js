import React, { useState } from "react";
import { BrowserRouter, Routes, Route, Link, useNavigate } from "react-router-dom";
import AddBook from "./AddBook";

function App() {
  // One state: array of books
  const [books, setBooks] = useState([
    { isbn: "978-0132350884", title: "Clean Code", author: "Robert C. Martin", price: 35.99 },
    { isbn: "978-0201633610", title: "Design Patterns", author: "GoF", price: 44.5 },
  ]);

  function addBook(newBook) {
    // Prevent duplicate ISBN
    const exists = books.some((b) => b.isbn === newBook.isbn);
    if (exists) {
      alert("ISBN already exists. Please use a different ISBN.");
      return;
    }
    setBooks((prev) => [...prev, newBook]);
  }

  function removeBook(isbn) {
    setBooks((prev) => prev.filter((b) => b.isbn !== isbn));
  }

  return (
      <BrowserRouter>
        <div style={{ maxWidth: 800, margin: "20px auto", fontFamily: "Arial" }}>
          <header style={{ display: "flex", gap: 12, alignItems: "center" }}>
            <h2 style={{ margin: 0 }}>Book Manager</h2>
            <nav style={{ marginLeft: "auto" }}>
              <Link to="/">Books</Link>{" | "}
              <Link to="/add">Add Book</Link>
            </nav>
          </header>

          <hr />

          <Routes>
            <Route path="/" element={<BooksList books={books} onRemove={removeBook} />} />
            <Route path="/add" element={<AddBook onAdd={addBook} />} />
          </Routes>
        </div>
      </BrowserRouter>
  );
}

function BooksList({ books, onRemove }) {
  return (
      <div>
        <h3>Books List</h3>

        {books.length === 0 ? (
            <p>No books yet. Click “Add Book”.</p>
        ) : (
            <table width="100%" border="1" cellPadding="8" style={{ borderCollapse: "collapse" }}>
              <thead>
              <tr>
                <th>ISBN</th>
                <th>Title</th>
                <th>Author</th>
                <th style={{ textAlign: "right" }}>Price</th>
                <th>Action</th>
              </tr>
              </thead>

              <tbody>
              {books.map((b) => (
                  <tr key={b.isbn}>
                    <td>{b.isbn}</td>
                    <td>{b.title}</td>
                    <td>{b.author}</td>
                    <td style={{ textAlign: "right" }}>${Number(b.price).toFixed(2)}</td>
                    <td>
                      <button onClick={() => onRemove(b.isbn)}>Remove</button>
                    </td>
                  </tr>
              ))}
              </tbody>
            </table>
        )}
      </div>
  );
}

function AddBook({ onAdd }) {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    isbn: "",
    title: "",
    author: "",
    price: "",
  });

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  function handleSubmit(e) {
    e.preventDefault();

    // Basic validation
    if (!form.isbn.trim() || !form.title.trim() || !form.author.trim() || form.price === "") {
      alert("Please fill in all fields.");
      return;
    }

    const priceNumber = Number(form.price);
    if (Number.isNaN(priceNumber) || priceNumber < 0) {
      alert("Price must be a valid number (0 or more).");
      return;
    }

    onAdd({
      isbn: form.isbn.trim(),
      title: form.title.trim(),
      author: form.author.trim(),
      price: priceNumber,
    });

    navigate("/"); // go back to list
  }

  return (
      <div>
        <h3>Add a New Book</h3>

        <form onSubmit={handleSubmit} style={{ display: "grid", gap: 10, maxWidth: 400 }}>
          <label>
            ISBN
            <input name="isbn" value={form.isbn} onChange={handleChange} />
          </label>

          <label>
            Title
            <input name="title" value={form.title} onChange={handleChange} />
          </label>

          <label>
            Author
            <input name="author" value={form.author} onChange={handleChange} />
          </label>

          <label>
            Price
            <input name="price" value={form.price} onChange={handleChange} />
          </label>

          <div style={{ display: "flex", gap: 10 }}>
            <button type="submit">Add</button>
            <button type="button" onClick={() => navigate("/")}>
              Cancel
            </button>
          </div>
        </form>
      </div>
  );
}

export default App;
