import { Autocomplete, TextField } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios';

const SearchSupplier = ({ onSelect }) => {
    const [options, setOptions] = useState([]);
    const [keyword, setKeyword] = useState('');

    useEffect(() => {
        const timer = setTimeout(() => {
            fetch(`/api/suppliers?keyword=${keyword}`)
                .then(res => res.json())
                .then(setOptions);
        }, 300); // debounce

        return () => clearTimeout(timer);
    }, [keyword]);

    return (
        <Autocomplete
            options={options}
            getOptionLabel={option => option.name}
            onInputChange={(e, value) => setKeyword(value)}
            onChange={(e, value) => onSelect(value)}
            renderInput={params => (
                <TextField {...params} label="Search supplier..." />
            )}
        />
    );
};
