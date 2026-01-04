import React, { createContext, useContext, useState } from "react";

const FormContext = createContext(null);

const initialForm = {
    firstname: "",
    lastname: "",
    profession: "Programmer",
    street: "",
    city: "",
    zip: "",
    state: "Iowa",
    creditcard: "",
    cardType: "Visa",
};

export function FormProvider({ children }) {
    const [form, setForm] = useState(initialForm); // ✅ ONE state object only

    // Generic updater: merges new field into the object
    function setField(name, value) {
        setForm((prev) => ({ ...prev, [name]: value }));
    }

    // Generic change handler for inputs/select/radio
    function handleChange(e) {
        const { name, value, type, checked } = e.target;

        // If later you add checkbox fields, this supports them too
        const finalValue = type === "checkbox" ? checked : value;
        setField(name, finalValue);
    }

    function reset() {
        setForm(initialForm);
    }

    return (
        <FormContext.Provider value={{ form, setField, handleChange, reset }}>
            {children}
        </FormContext.Provider>
    );
}

export function useFormData() {
    const ctx = useContext(FormContext);
    if (!ctx) throw new Error("useFormData must be used inside FormProvider");
    return ctx;
}
