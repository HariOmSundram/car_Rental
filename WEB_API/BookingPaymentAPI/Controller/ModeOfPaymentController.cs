using Microsoft.AspNetCore.Mvc;
using BookingPaymentAPI.Data;
using BookingPaymentAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace BookingPaymentAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ModeOfPaymentController : ControllerBase
    {
        private readonly BookingPaymentDbContext _context;

        public ModeOfPaymentController(BookingPaymentDbContext context)
        {
            _context = context;
        }

        // GET: api/ModeOfPayment
        [HttpGet]
        public async Task<IActionResult> GetAllModes()
        {
            return Ok(await _context.ModeOfPayment.ToListAsync());
        }

        // GET: api/ModeOfPayment/{id}
        [HttpGet("{id}")]
        public async Task<IActionResult> GetModeById(int id)
        {
            var mode = await _context.ModeOfPayment.FindAsync(id);
            if (mode == null) return NotFound();
            return Ok(mode);
        }

        // POST: api/ModeOfPayment
        [HttpPost]
        public async Task<IActionResult> CreateMode(ModeOfPayment mode)
        {
            _context.ModeOfPayment.Add(mode);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetModeById), new { id = mode.ModeId }, mode);
        }

        // PUT: api/ModeOfPayment/{id}
        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateMode(int id, ModeOfPayment updatedMode)
        {
            if (id != updatedMode.ModeId) return BadRequest();

            _context.Entry(updatedMode).State = EntityState.Modified;
            await _context.SaveChangesAsync();
            return NoContent();
        }

        // DELETE: api/ModeOfPayment/{id}
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteMode(int id)
        {
            var mode = await _context.ModeOfPayment.FindAsync(id);
            if (mode == null) return NotFound();

            _context.ModeOfPayment.Remove(mode);
            await _context.SaveChangesAsync();
            return NoContent();
        }
    }
}
