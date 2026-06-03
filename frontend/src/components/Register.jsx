import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerApi } from '../services/authService';

const Register = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async(e) => {
    e.preventDefault();

    if (!username.trim() || !email.trim() || !password.trim() || !confirmPassword.trim()) {
      setError('Please enter all fields.');
      return;
    }

    if (password !== confirmPassword) {
      setError('Passwords do not match.');
      return;
    }

    try {
      const res = await registerApi({ username, email, password });

      const { message, username: registerUsername, token } = res.data;
      
      if (!registerUsername) {
        setError(message || "Registration failed");
        return;
      }

      alert(message);
      setError('');
      navigate('/login');

    } catch (err) {
      setError(err.response?.data?.message || err.message);
      return;
    }
}

  return (
    <div className="min-h-screen bg-slate-900 text-slate-100 flex items-center justify-center px-4 py-10">
      <div className="w-full max-w-md bg-slate-800/95 border border-slate-700 rounded-3xl shadow-2xl p-8">
        <div className="mb-8 text-center">
          <h1 className="text-3xl font-semibold">Sign Up</h1>
          <p className="mt-2 text-slate-400">Enter your information to create an account</p>
        </div>

        {error && (
          <div className="mb-4 rounded-lg bg-red-500/10 border border-red-400 text-red-100 px-4 py-3">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label className="block text-sm font-medium text-slate-300 mb-2" htmlFor="email">
              Email
            </label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full rounded-2xl border border-slate-700 bg-slate-900 px-4 py-3 text-slate-100 focus:outline-none focus:ring-2 focus:ring-sky-500"
              placeholder="Enter email"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-300 mb-2" htmlFor="username">
              Username
            </label>
            <input
              id="username"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full rounded-2xl border border-slate-700 bg-slate-900 px-4 py-3 text-slate-100 focus:outline-none focus:ring-2 focus:ring-sky-500"
              placeholder="Enter username"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-300 mb-2" htmlFor="password">
              Password
            </label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full rounded-2xl border border-slate-700 bg-slate-900 px-4 py-3 text-slate-100 focus:outline-none focus:ring-2 focus:ring-sky-500"
              placeholder="Enter password"
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-slate-300 mb-2" htmlFor="confirm-password">
              Confirm password
            </label>
            <input
              id="confirm-password"
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              className="w-full rounded-2xl border border-slate-700 bg-slate-900 px-4 py-3 text-slate-100 focus:outline-none focus:ring-2 focus:ring-sky-500"
              placeholder="Enter confirm password"
            />
          </div>

          <button
            type="submit"
            className="w-full rounded-2xl bg-sky-500 px-4 py-3 text-base font-semibold text-white transition hover:bg-sky-400"
          >
            Sign Up
          </button>
        </form>

        <div className="mt-6 text-center text-slate-500 text-sm">
          Already have an account?{' '}
          <a href="/login" className="font-semibold text-sky-500 hover:text-sky-400">
            Sign In
          </a>
        </div>
      </div>
    </div>
  );
};

export default Register;
