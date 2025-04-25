import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    TextField,
} from '@mui/material';
import { useEffect } from 'react';
import { useForm } from 'react-hook-form';

interface ProductForm {
    name: string;
    salePrice: number;
}

interface ProductModalProps {
    open: boolean;
    // mode: 'edit';
    initialValues?: ProductForm;
    onClose: () => void;
    onSubmit: (data: ProductForm) => void;
}

const ProductModal = ({
    open,
    // mode,
    initialValues,
    onClose,
    onSubmit,
}: ProductModalProps) => {
    const { register, handleSubmit, reset } = useForm<ProductForm>({
        defaultValues: initialValues || {
            name: '',
            salePrice: 0,
        },
    });

    useEffect(() => {
        reset(initialValues || { name: '', salePrice: 0 });
    }, [initialValues, open, reset]);

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>Edit Product</DialogTitle>
            <form onSubmit={handleSubmit(onSubmit)}>
                <DialogContent>
                    <TextField
                        fullWidth
                        label="Name"
                        {...register('name')}
                        margin="dense"
                    />

                    <TextField
                        fullWidth
                        label="Sale Price"
                        type="number"
                        {...register('salePrice')}
                        margin="dense"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button type="submit" variant="contained">
                        Save
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default ProductModal;
