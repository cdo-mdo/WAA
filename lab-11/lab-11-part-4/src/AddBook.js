import { useState } from "react";
import { useNavigate } from "react-router-dom";

function AddBook({ onAdd }) {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        isbn: "",
        title: "",
        author: "",
        price: "",
    });

    const [errors, setErrors] = useState({});

    function handleChange(e) {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    }

    function validate() {
        const errors = {};
        if (!form.isbn.trim()) errors.isbn = "ISBN is required";
        if (!form.title.trim()) errors.title = "Title is required";
        if (!form.author.trim()) errors.author = "Author is required";
        if (form.price === "" || isNaN(form.price) || Number(form.price) < 0)
            errors.price = "Valid price required";
        return errors;
    }

    function handleSubmit(e) {
        e.preventDefault();
        const validationErrors = validate();
        setErrors(validationErrors);

        if (Object.keys(validationErrors).length === 0) {
            onAdd({
                isbn: form.isbn.trim(),
                title: form.title.trim(),
                author: form.author.trim(),
                price: Number(form.price),
            });
            navigate("/");
        }
    }

    return (
        <div>
            <h3>Add Book</h3>

            <form onSubmit={handleSubmit}>
                <div>
                    ISBN:
                    <input name="isbn" value={form.isbn} onChange={handleChange} />
                    {errors.isbn && <span style={{ color: "red" }}>{errors.isbn}</span>}
                </div>

                <div>
                    Title:
                    <input name="title" value={form.title} onChange={handleChange} />
                    {errors.title && <span style={{ color: "red" }}>{errors.title}</span>}
                </div>

                <div>
                    Author:
                    <input name="author" value={form.author} onChange={handleChange} />
                    {errors.author && <span style={{ color: "red" }}>{errors.author}</span>}
                </div>

                <div>
                    Price:
                    <input name="price" value={form.price} onChange={handleChange} />
                    {errors.price && <span style={{ color: "red" }}>{errors.price}</span>}
                </div>

                <br />
                <button type="submit">Add</button>
            </form>
        </div>
    );
}

export default AddBook;
