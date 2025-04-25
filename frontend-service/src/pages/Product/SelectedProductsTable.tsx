import {
    Table,
    TableHead,
    TableBody,
    TableRow,
    TableCell,
    TableContainer,
    Paper,
    Button,
    Box,
} from '@mui/material';

export interface SelectedProduct {
    productId: number;
    productName: string;
    importPrice: number;
    quantity: number;
    salePrice: number;
}

interface SelectedProductsTableProps {
    products: SelectedProduct[];
    onEdit: (product: SelectedProduct) => void;
    onRemove: (productId: number) => void;
}

const SelectedProductsTable = ({
    products,
    onEdit,
    onRemove,
}: SelectedProductsTableProps) => {
    return (
        <Box sx={{ height: '85%', paddingBottom: 1 }}>
            <TableContainer
                component={Paper}
                sx={{
                    maxHeight: '100%',
                    overflowY: 'scroll',
                    borderRadius: 2,
                    border: '1px solid #e7e9ef ',
                    scrollbarWidth: 'none',
                }}
            >
                <Table size="small" stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                ID
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Product Name
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Import Price
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Quantity
                            </TableCell>

                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Actions
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products.map(p => (
                            <TableRow key={p.productId}>
                                <TableCell>{p.productId}</TableCell>
                                <TableCell>{p.productName}</TableCell>
                                <TableCell>{p.importPrice}</TableCell>
                                <TableCell>{p.quantity}</TableCell>

                                <TableCell>
                                    <Button
                                        onClick={() => onEdit(p)}
                                        size="small"
                                    >
                                        Edit
                                    </Button>
                                    <Button
                                        onClick={() => onRemove(p.productId)}
                                        size="small"
                                        color="error"
                                    >
                                        Remove
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
        //         <Box sx={{ padding: 2 }}>
    );
};

export default SelectedProductsTable;
