import { useEffect, useState } from "react";
import { api } from "./api";

function Money({ value }) {
    const n = Number(value ?? 0);
    return <span>${n.toFixed(2)}</span>;
}

export default function App() {
    const [accountNumber, setAccountNumber] = useState("");
    const [accountHolder, setAccountHolder] = useState("");

    const [selectedAcc, setSelectedAcc] = useState("");
    const [account, setAccount] = useState(null);

    const [amount, setAmount] = useState("");
    const [message, setMessage] = useState("");

    async function createAccount(e) {
        e.preventDefault();
        setMessage("");
        try {
            const res = await api.post("/accounts", {
                accountNumber: Number(accountNumber),
                accountHolder,
            });
            setMessage(`Created account ${res.data.accountNumber}`);
            setSelectedAcc(String(res.data.accountNumber));
            setAccount(res.data);
        } catch (err) {
            setMessage(err?.response?.data?.error || err.message);
        }
    }

    async function loadAccount(no = selectedAcc) {
        setMessage("");
        try {
            const res = await api.get(`/accounts/${Number(no)}`);
            setAccount(res.data);
        } catch (err) {
            setAccount(null);
            setMessage(err?.response?.data?.error || err.message);
        }
    }

    async function deposit() {
        setMessage("");
        try {
            const res = await api.post(`/accounts/${Number(selectedAcc)}/deposit`, {
                amount: Number(amount),
            });
            setAccount(res.data);
            setAmount("");
        } catch (err) {
            setMessage(err?.response?.data?.error || err.message);
        }
    }

    async function withdraw() {
        setMessage("");
        try {
            const res = await api.post(`/accounts/${Number(selectedAcc)}/withdraw`, {
                amount: Number(amount),
            });
            setAccount(res.data);
            setAmount("");
        } catch (err) {
            setMessage(err?.response?.data?.error || err.message);
        }
    }

    async function removeAccount() {
        setMessage("");
        try {
            await api.delete(`/accounts/${Number(selectedAcc)}`);
            setMessage(`Removed account ${selectedAcc}`);
            setAccount(null);
            setSelectedAcc("");
        } catch (err) {
            setMessage(err?.response?.data?.error || err.message);
        }
    }

    useEffect(() => {
        // optional: nothing to auto-load because backend is in-memory
    }, []);

    return (
        <div style={{ maxWidth: 900, margin: "30px auto", fontFamily: "Arial" }}>
            <h2>Bank REST UI</h2>

            {message && (
                <div style={{ padding: 10, background: "#f3f3f3", marginBottom: 12 }}>
                    {message}
                </div>
            )}

            <section style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 16 }}>
                <div style={{ border: "1px solid #ddd", padding: 16 }}>
                    <h3>Create Account</h3>
                    <form onSubmit={createAccount} style={{ display: "grid", gap: 8 }}>
                        <input
                            placeholder="Account Number (e.g., 1001)"
                            value={accountNumber}
                            onChange={(e) => setAccountNumber(e.target.value)}
                        />
                        <input
                            placeholder="Account Holder (e.g., Cuong Do)"
                            value={accountHolder}
                            onChange={(e) => setAccountHolder(e.target.value)}
                        />
                        <button type="submit">Create</button>
                    </form>
                </div>

                <div style={{ border: "1px solid #ddd", padding: 16 }}>
                    <h3>Load Account</h3>
                    <div style={{ display: "grid", gap: 8 }}>
                        <input
                            placeholder="Account Number to view"
                            value={selectedAcc}
                            onChange={(e) => setSelectedAcc(e.target.value)}
                        />
                        <button onClick={() => loadAccount()}>Get Account</button>
                        <button onClick={removeAccount} disabled={!selectedAcc}>
                            Remove Account
                        </button>
                    </div>
                </div>
            </section>

            <section style={{ border: "1px solid #ddd", padding: 16, marginTop: 16 }}>
                <h3>Deposit / Withdraw</h3>
                <div style={{ display: "flex", gap: 8, alignItems: "center" }}>
                    <input
                        placeholder="Amount (e.g., 50.00)"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        style={{ width: 200 }}
                    />
                    <button onClick={deposit} disabled={!selectedAcc || !amount}>
                        Deposit
                    </button>
                    <button onClick={withdraw} disabled={!selectedAcc || !amount}>
                        Withdraw
                    </button>
                </div>
            </section>

            <section style={{ border: "1px solid #ddd", padding: 16, marginTop: 16 }}>
                <h3>Account Details</h3>
                {!account ? (
                    <div>No account loaded.</div>
                ) : (
                    <>
                        <div><b>Account Number:</b> {account.accountNumber}</div>
                        <div><b>Account Holder:</b> {account.accountHolder}</div>
                        <div><b>Balance:</b> <Money value={account.balance} /></div>

                        <h4 style={{ marginTop: 12 }}>Transactions</h4>
                        {account.transactions?.length ? (
                            <table width="100%" border="1" cellPadding="6" style={{ borderCollapse: "collapse" }}>
                                <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Type</th>
                                    <th>Amount</th>
                                </tr>
                                </thead>
                                <tbody>
                                {account.transactions.map((t, idx) => (
                                    <tr key={idx}>
                                        <td>{t.date}</td>
                                        <td>{t.type}</td>
                                        <td><Money value={t.amount} /></td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        ) : (
                            <div>No transactions yet.</div>
                        )}
                    </>
                )}
            </section>
        </div>
    );
}
